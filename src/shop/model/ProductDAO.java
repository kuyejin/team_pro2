package shop.model;

import shop.dto.Category;
import shop.dto.Fileud;
import shop.dto.Product;
import shop.dto.Receive;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProductDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public List<Product> getProductList() {
        List<Product> proList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Product pro = new Product();
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
                proList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return proList;
    }


    public List<Product> getCateProductListSchool(String cate){
        List<Product> proList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        String cate1="";
        String cate2="";
        String cate3="";
        String cate4="";
        if (cate.equals("초등")) { cate1="A";cate2="B";cate3="C";cate4="D";}
        else if(cate.equals("중등")){ cate1="E";cate2="F";cate3="G";cate4="H";}
        else if(cate.equals("고등")) { cate1="I";cate2="J";cate3="K";cate4="L";}
        else if(cate.equals("기타")) { cate1="M";cate2="N";cate3="O";cate4="P";}

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_CATE_SCHOOL);

            pstmt.setString(1, cate1);
            pstmt.setString(2, cate2);
            pstmt.setString(3, cate3);
            pstmt.setString(4, cate4);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Product pro = new Product();
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setCateno(rs.getString("cateno"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));
                pro.setImgSrc2(rs.getString("imgsrc2"));
                pro.setImgSrc3(rs.getString("imgsrc3"));
                pro.setCname(rs.getString("cname"));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
                proList.add(pro);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return proList;
    }


    public List<Product> getCateProductList(String cate) {
        List<Product> proList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_CATE);
            pstmt.setString(1, cate);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Product pro = new Product();
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));
                pro.setCname(rs.getString("cname"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
                proList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return proList;
    }

    public Product getProduct(int no) {
        Product pro = new Product();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_ONE);
            pstmt.setInt(1,no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setCateno(rs.getString("cateno"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));
                pro.setImgSrc2(rs.getString("imgsrc2"));
                pro.setImgSrc3(rs.getString("imgsrc3"));
                pro.setCname(rs.getString("cname"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return pro;
    }

    public int addProduct(Product pro){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        String sql = DBConnect.PRODUCT_INSERT;
        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, pro.getCate());
            pstmt.setString(2, pro.getPname());
            pstmt.setString(3, pro.getPcomment());
            pstmt.setString(4, pro.getPlist());
            pstmt.setInt(5, pro.getPrice());
            pstmt.setString(6, pro.getImgSrc1()); // 메인이미지
            pstmt.setString(7, pro.getImgSrc2()); // 설명이미지
            pstmt.setString(8, pro.getImgSrc3()); // 소개영상
            System.out.println("img1" + pro.getImgSrc1());
            System.out.println("img2" + pro.getImgSrc2());
            System.out.println("img3" + pro.getImgSrc3());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }

        con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_INSERT_CATENO);
            cnt = cnt + pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }

        return cnt;
    }

    public int updateProduct(Product pro){
        int cnt =0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();

        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_UPDATE);
            pstmt.setString(1, pro.getPname());
            pstmt.setString(2, pro.getPcomment());
            pstmt.setString(3, pro.getPlist());
            pstmt.setInt(4, pro.getPrice());
            pstmt.setString(5, pro.getImgSrc1());
            pstmt.setString(6, pro.getImgSrc2());
            pstmt.setString(7, pro.getImgSrc3());
            pstmt.setString(8,pro.getCate());
            pstmt.setInt(9, pro.getNo());

            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }

        con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_INSERT_CATENO);
            cnt = cnt + pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }


        return cnt;
    }

    public int delProduct(int no){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        String sql = DBConnect.PRODUCT_DELETE;
        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, no);
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public List<Category> getCategoryList(){
        List<Category> cateList = new ArrayList<Category>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.CATEGORY_LOAD);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Category cate = new Category();
                cate.setCno(rs.getString("cno"));
                cate.setCname(rs.getString("cname"));
                cateList.add(cate);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cateList;
    }

    public int getAmount(int pno){
        int amount = 0;
        DBConnect con = new MariaDBCon();
        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.INVENTORY_SELECT_ONE);
            pstmt.setInt(1, pno);
            rs = pstmt.executeQuery();
            if(rs.next()){
                amount = rs.getInt("amount");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return amount;
    }

    public int addReceive(Receive rec){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.RECEIVE_INSERT);
            pstmt.setInt(1, rec.getPno());
            pstmt.setInt(2, rec.getAmount());
            pstmt.setInt(3, rec.getRprice());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }


    public int updateReceive(Receive rec){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.RECEIVE_AMOUNT_UPDATE);
            pstmt.setInt(1, rec.getAmount());
            pstmt.setInt(2, rec.getPno());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public String getCateName(String cate) {
        String catename ="";

        DBConnect con = new MariaDBCon();
        conn = con.connect();
        String sql = "select * from category where cno=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cate);
            rs = pstmt.executeQuery();
            if(rs.next()){
                catename = rs.getString("cname");

            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }

        return catename;
    }

    public List<Product> getProductListBest() {
        List<Product> proList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_BEST);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Product pro = new Product();
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));
                pro.setCname(rs.getString("cname"));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
                proList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return proList;
    }

    public List<Product> getProductListNew() {
        List<Product> proList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        try {
            pstmt = conn.prepareStatement(DBConnect.PRODUCT_SELECT_NEW);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Product pro = new Product();
                pro.setNo(rs.getInt("no"));
                pro.setCate(rs.getString("cate"));
                pro.setPname(rs.getString("pname"));
                pro.setPcomment(rs.getString("pcomment"));
                pro.setPlist(rs.getString("plist"));
                pro.setPrice(rs.getInt("price"));
                pro.setImgSrc1(rs.getString("imgsrc1"));
                pro.setCname(rs.getString("cname"));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                pro.setResdate(date);
                proList.add(pro);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return proList;
    }
}
