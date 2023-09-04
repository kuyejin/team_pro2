package shop.model;

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

public class NoticeDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public List<Notice> getNoticeList(){
        List<Notice> notiList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.NOTICE_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Notice noti = new Notice();
                noti.setNo(rs.getInt("no"));
                noti.setTitle(rs.getString("title"));
                noti.setContent(rs.getString("content"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                noti.setResdate(date);
                noti.setVisited(rs.getInt("visited"));
                notiList.add(noti);
            }
            System.out.println("공지사항 추출 완료");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return notiList;
    }

    public Notice getNotice(int no){
        Notice noti = new Notice();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        conn = con.connect();
        if(conn!=null){
            System.out.println("PostgreSQL 연결 성공");
        }

        try {
            pstmt = conn.prepareStatement(DBConnect.NOTICE_SELECT_ONE);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();

            if(rs.next()){
                noti.setNo(rs.getInt("no"));
                noti.setTitle(rs.getString("title"));
                noti.setContent(rs.getString("content"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                noti.setResdate(date);
                noti.setVisited(rs.getInt("visited"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
    updateCnt(no);
    return noti;
    }
    
    // 조회수 갱신
    public void updateCnt(int no) {
        Notice noti = new Notice();
        DBConnect con = new MariaDBCon();

        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.NOTICE_UPDATE_CNT);
            pstmt.setInt(1, no);
            int cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
    }
    

    public int addNotice(Notice noti){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.NOTICE_INSERT);
            pstmt.setString(1, noti.getTitle());
            pstmt.setString(2, noti.getContent());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int updateNotice(Notice noti){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria updateNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.NOTICE_UPDATE);
            pstmt.setString(1, noti.getTitle());
            pstmt.setString(2, noti.getContent());
            pstmt.setInt(3, noti.getNo());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int deleteNotice(int no){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria deleteNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.NOTICE_DELETE);
            pstmt.setInt(1, no);
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }
}
