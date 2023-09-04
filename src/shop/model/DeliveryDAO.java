package shop.model;

import shop.dto.Delivery;
import shop.dto.DeliveryInfoView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    String sql = "";

    //배송 등록(DeliveryDAO.addDelivery(del))
    public int addDelivery(Delivery del){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_INSERT);
            pstmt.setInt(1, del.getSno());
            pstmt.setString(2, del.getCid());
            pstmt.setString(3, del.getDaddr());
            pstmt.setString(4, del.getCustel());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    //결제번호를 통한 배송정보 조회
    public Delivery getBySnoDelivery(int sno){
        Delivery del = new Delivery();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_SELECT_SNO);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                del.setDno(rs.getInt("dno"));
                del.setSno(rs.getInt("sno"));
                del.setCid(rs.getString("cid"));
                del.setDaddr(rs.getString("daddr"));
                del.setCustel(rs.getString("custel"));
                del.setPcom(rs.getString("pcom"));
                del.setPtel(rs.getString("ptel"));
                del.setPstate(rs.getInt("pstate"));
                del.setSdate(rs.getString("sdate"));
                del.setRdate(rs.getString("rdate"));
                del.setBcode(rs.getString("bcode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return del;
    }

    //배송 정보 조회
    public int getDelivery(int pno, String cid){
        int check = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_SELECT_BUY);
            pstmt.setInt(1, pno);
            pstmt.setString(2, cid);
            rs = pstmt.executeQuery();
            if(rs.next()){
                check = 1;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return check;
    }

    public Delivery getDelivery(int dno){
        Delivery del = new Delivery();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_SELECT_ONE);
            pstmt.setInt(1, dno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                del.setDno(rs.getInt("dno"));
                del.setSno(rs.getInt("sno"));
                del.setCid(rs.getString("cid"));
                del.setDaddr(rs.getString("daddr"));
                del.setCustel(rs.getString("custel"));
                del.setPcom(rs.getString("pcom"));
                del.setPtel(rs.getString("ptel"));
                del.setPstate(rs.getInt("pstate"));
                del.setSdate(rs.getString("sdate"));
                del.setRdate(rs.getString("rdate"));
                del.setBcode(rs.getString("bcode"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return del;
    }

    // 구매 확정
    public boolean buyDelivery(int sno) {
        boolean check = false;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_BUY);
            pstmt.setInt(1,sno);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                check = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return check;
    }

    public List<Delivery> getDeliveryList(int pstate){
        List<Delivery> deliveryList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_SELECT_ALL);
            pstmt.setInt(1, pstate);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Delivery del = new Delivery();
                del.setDno(rs.getInt("dno"));
                del.setSno(rs.getInt("sno"));
                del.setCid(rs.getString("cid"));
                del.setDaddr(rs.getString("daddr"));
                del.setCustel(rs.getString("custel"));
                del.setPcom(rs.getString("pcom"));
                del.setPtel(rs.getString("ptel"));
                del.setPstate(rs.getInt("pstate"));
                del.setSdate(rs.getString("sdate"));
                del.setRdate(rs.getString("rdate"));
                del.setBcode(rs.getString("bcode"));
                deliveryList.add(del);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return deliveryList;
    }

    //배송 송장 등록 및 배송시작
    public int updateDelivery(Delivery del){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_PRO);
            pstmt.setString(1, del.getPcom());
            pstmt.setString(2, del.getPtel());
            pstmt.setInt(3, del.getPstate());
            pstmt.setString(4, del.getRdate());
            pstmt.setString(5, del.getBcode());
            pstmt.setInt(6, del.getDno());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }


    // 배송 뷰 보기
    public DeliveryInfoView deliveryInfoViewList(int sno) {
        DeliveryInfoView divl = new DeliveryInfoView();

        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try{
            pstmt = conn.prepareStatement(DBConnect.DELIVERY_VIEW);
            pstmt.setInt(1, sno);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                divl.setDno(rs.getInt("dno"));
                divl.setSno(rs.getInt("sno"));
                divl.setCid(rs.getString("cid"));
                divl.setDaddr(rs.getString("daddr"));
                divl.setCustel(rs.getString("custel"));
                divl.setPcom(rs.getString("pcom"));
                divl.setPtel(rs.getString("ptel"));
                divl.setPstate(rs.getInt("pstate"));
                divl.setSdate(rs.getString("sdate"));
                divl.setRdate(rs.getString("rdate"));
                divl.setBcode(rs.getString("bcode"));
                divl.setPname(rs.getString("pname"));
                divl.setAmount(rs.getInt("amount"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return  divl;
    }

}
