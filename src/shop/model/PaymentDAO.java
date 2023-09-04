package shop.model;

import shop.dto.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    String sql = "";

    //결제 처리(PaymentDAO.addPayment(pay))
    public int addPayment(Payment pay){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.SERVE_PAYMENT);
            pstmt.setString(1, pay.getCid());
            pstmt.setInt(2, pay.getPno());
            pstmt.setInt(3, pay.getAmount());
            pstmt.setString(4, pay.getPmethod());
            pstmt.setString(5, pay.getPcom());
            pstmt.setString(6, pay.getCnum());
            pstmt.setInt(7, pay.getPayprice());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    //출고 처리(PaymentDAO.addServe(serv))
    public int addServe(Serve serv){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.SERVE_INSERT);
            pstmt.setInt(1, serv.getPno());
            pstmt.setInt(2, serv.getAmount());
            pstmt.setInt(3, serv.getSprice());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int getSno(){
        int sno = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.GET_SNO);
            rs = pstmt.executeQuery();
            if(rs.next()){
                sno = rs.getInt("sno");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return sno;
    }

    public int returnPayment(int sno, int pno, int amount, String cid) {
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            conn.setAutoCommit(false);

            //1. 반품시 결제 내용 제거
            pstmt = conn.prepareStatement(DBConnect.RETURN_PAYMENT);
            pstmt.setInt(1, sno);
            cnt = pstmt.executeUpdate();

            //2. 반품시 배송 정보 삭제
            pstmt = conn.prepareStatement(DBConnect.RETURN_DELEVERY);
            pstmt.setInt(1, sno);
            cnt = cnt + pstmt.executeUpdate();

            //3. 반품시 출고 삭제
            pstmt = conn.prepareStatement(DBConnect.RETURN_SERVE);
            pstmt.setInt(1, sno);
            cnt = cnt + pstmt.executeUpdate();

            //4. 반품시 장바구니에 다시 담기
            pstmt = conn.prepareStatement(DBConnect.CART_SELECT_FIND);
            pstmt.setString(1, cid);
            pstmt.setInt(2, pno);
            rs = pstmt.executeQuery();

            if(!rs.next()) {
                pstmt = conn.prepareStatement(DBConnect.RETURN_CART);
                pstmt.setString(1, cid);
                pstmt.setInt(2, pno);
                pstmt.setInt(3, amount);
                cnt = cnt + pstmt.executeUpdate();
            }

            conn.commit();
            conn.setAutoCommit(true);
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return cnt;
    }

    public Payment getPayment(int sno){
        Payment pay = new Payment();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.PAYMENT_SELECT_ONE);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                pay.setSno(rs.getInt("sno"));
                pay.setCid(rs.getString("cid"));
                pay.setPno(rs.getInt("pno"));
                pay.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return pay;
    }

    public int getPaymentBuyCustom(int pno, String cid) {
        int check = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.PAYMENT_SELECT_CID_PNO);
            pstmt.setString(1,cid);
            pstmt.setInt(2, pno);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                check = 1;
                System.out.println(cid + "는 " + pno + " 상품을 구매한 유저 입니다.");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }


    public List<PaymentVO> getCidPaymentList(String cid){
        List<PaymentVO> payList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.PAYMENT_SELECT_CID);
            pstmt.setString(1, cid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                PaymentVO pay = new PaymentVO();
                pay.setSno(rs.getInt("sno"));
                pay.setCid(rs.getString("cid"));
                pay.setPno(rs.getInt("pno"));
                pay.setAmount(rs.getInt("amount"));
                pay.setPmethod(rs.getString("pmethod"));
                pay.setPcom(rs.getString("pcom"));
                pay.setCnum(rs.getString("cnum"));
                pay.setPayprice(rs.getInt("payprice"));
                pay.setPname(getVOPname(pay.getPno()));
                pay.setPstate(getVOState(pay.getSno()));
                payList.add(pay);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return payList;
    }

    public String getVOPname(int pno){
        ProductDAO dao = new ProductDAO();
        Product pro  = dao.getProduct(pno);
        return pro.getPname();
    }

    public int getVOState(int sno){
        DeliveryDAO dao = new DeliveryDAO();
        Delivery del  = dao.getBySnoDelivery(sno);
        return del.getPstate();
    }

    public List<Sales> getSales() {
        List<Sales> salesList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.SALES_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Sales s = new Sales();
                s.setPno(rs.getInt("pno"));
                s.setCnt(rs.getInt("total_amount"));
                s.setMoney(rs.getInt("total_cost"));
                s.setPname(rs.getString("pname"));
                salesList.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return salesList;
    }
}
