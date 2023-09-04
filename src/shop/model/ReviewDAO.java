package shop.model;

import shop.dto.Notice;
import shop.dto.Product;
import shop.dto.Qna;
import shop.dto.Review;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ReviewDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    public List<Review> getReviewList(int no){
        List<Review> revList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.REVIEW_SELECT_ALL_PAR);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Review rv = new Review();
                rv.setNo(rs.getInt("no"));
                rv.setCid(rs.getString("cid"));
                rv.setContent(rs.getString("content"));
                rv.setPar(rs.getInt("par"));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                rv.setResdate(date);
                revList.add(rv);
                System.out.println(rv.toString());
            }
            System.out.println("리뷰 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return revList;
    }

    public List<Review> getReviewList(String cid){
        List<Review> revList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.REVIEW_SELECT_ALL_CID);
            pstmt.setString(1, cid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Review rv = new Review();
                rv.setNo(rs.getInt("no"));
                rv.setCid(rs.getString("cid"));
                rv.setContent(rs.getString("content"));
                rv.setPar(rs.getInt("par"));
                rv.setPname(getVOPname(rv.getPar()));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                rv.setResdate(date);
                revList.add(rv);
                System.out.println(rv.toString());
            }
            System.out.println("리뷰 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return revList;
    }

    public List<Review> getReviewList(){
        List<Review> revList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.REVIEW_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Review rv = new Review();
                rv.setNo(rs.getInt("no"));
                rv.setCid(rs.getString("cid"));
                rv.setContent(rs.getString("content"));
                rv.setPar(rs.getInt("par"));
                rv.setPname(getVOPname(rv.getPar()));
                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                rv.setResdate(date);
                revList.add(rv);
                System.out.println(rv.toString());
            }
            System.out.println("리뷰 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return revList;
    }

    public Review getReview(int no, String cid){
        Review rv = new Review();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.REVIEW_SELECT_ONE);
            pstmt.setInt(1, no);
            pstmt.setString(2, cid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                rv.setNo(rs.getInt("no"));
                rv.setCid(rs.getString("cid"));
                rv.setContent(rs.getString("content"));
                rv.setPar(rs.getInt("par"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                rv.setResdate(date);
                System.out.println(rv.toString());
            }
            System.out.println("리뷰 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return rv;
    }

    public boolean reviewCheck(String cid, int par){
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");
        boolean ck = true;
        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.REVIEW_SELECT_CK);
            pstmt.setInt(1, par);
            pstmt.setString(2, cid);
            rs = pstmt.executeQuery();
            while(rs.next()){
                ck = false;
            }
            System.out.println("리뷰 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return ck;
    }

    public int addReview(Review rv){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        Connection conn = null;
        PreparedStatement pstmt = null;

        // par 를 넣기전에 질문 게시글을 db에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.REVIEW_INSERT;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rv.getCid());
            pstmt.setString(2, rv.getContent());
            pstmt.setInt(3, rv.getPar());
            cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("리뷰 생성 완료");
            } else {
                System.out.println("리뷰 생성 실패");
            }
        } catch (SQLException e) {
            System.out.println("리뷰 생성: sql 에러");
        } catch (Exception e) {

        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }
        return cnt;
    }

    public int updateReview(Review rv){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        Connection conn = null;
        PreparedStatement pstmt = null;

        // par 를 넣기전에 질문 게시글을 db에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.REVIEW_UPDATE;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rv.getContent());
            pstmt.setString(2, rv.getCid());
            pstmt.setInt(3, rv.getPar());
            cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("리뷰 수정 완료");
            } else {
                System.out.println("리뷰 수정 실패");
            }
        } catch (SQLException e) {
            System.out.println("리뷰 수정: sql 에러");
        } catch (Exception e) {

        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }
        return cnt;
    }

    public int deleteReview(Review rv){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        Connection conn = null;
        PreparedStatement pstmt = null;

        // par 를 넣기전에 질문 게시글을 db에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.REVIEW_DELETE;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, rv.getCid());
            pstmt.setInt(2, rv.getPar());
            cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("리뷰 삭제 완료");
            } else {
                System.out.println("리뷰 삭제 실패");
            }
        } catch (SQLException e) {
            System.out.println("리뷰 삭제: sql 에러");
        } catch (Exception e) {

        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }
        return cnt;
    }

    public String getVOPname(int pno){
        ProductDAO dao = new ProductDAO();
        Product pro  = dao.getProduct(pno);
        return pro.getPname();
    }
}
