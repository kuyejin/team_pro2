package shop.model;

import shop.dto.Faq;
import shop.dto.Notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*public class FaqDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    /*public List<Faq> getfaqList() {
        List<Faq> faqlist = new ArrayList<>();
        DBConnect con = new MariaDBCon();

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.FAQ_SELECT_ALL);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Faq f = new Faq();
                f.setFno(rs.getInt("fno"));
                f.setQuestion(rs.getString("question"));
                f.setAnswer(rs.getString("answer"));
                faqlist.add(f);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return faqlist;
    }

    public Faq getFaq(int fno){
        Faq f = new Faq();
        DBConnect con = new MariaDBCon();

        conn = con.connect();
        if(conn!=null){
            System.out.println("DB 연결 성공");
        }

        try {
            pstmt = conn.prepareStatement(DBConnect.FAQ_SELECT_ONE);
            pstmt.setInt(1, fno);
            rs = pstmt.executeQuery();

            if(rs.next()){
                f.setFno(rs.getInt("fno"));
                f.setQuestion(rs.getString("question"));
                f.setAnswer(rs.getString("answer"));
                f.setCnt(rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        updateCnt(fno);
        return f;
    }

    public void updateCnt(int fno) {
        Faq f = new Faq();
        DBConnect con = new MariaDBCon();

        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.FAQ_UPDATE_CNT);
            pstmt.setInt(1, fno);
            int cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
    }

    public int addFaq(Faq f){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.FAQ_INSERT);
            pstmt.setString(1, f.getQuestion());
            pstmt.setString(2, f.getAnswer()));
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int updateFaq(Faq f){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria updateNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.FAQ_UPDATE);
            pstmt.setString(1, f.getQuestion());
            pstmt.setString(2, f.getAnswer());
            pstmt.setInt(3, f.getFno());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int deleteFaq(int fno){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria deleteNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.FAQ_DELETE);
            pstmt.setInt(1, fno);
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }


}*/