package shop.model;

import shop.dto.Notice;
import shop.dto.Qna;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QnaDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;

    public List<Qna> getQnaList(){
        List<Qna> qnaList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.QNA_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()){
                Qna q = new Qna();
                q.setQno(rs.getInt("qno"));
                q.setPar(rs.getInt("par"));
                q.setTitle(rs.getString("title"));
                q.setCnt(rs.getInt("cnt"));
                q.setCid(rs.getString("cid"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                q.setResdate(date);
                qnaList.add(q);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return qnaList;
    }

    public Qna getBoard(int no){
        Qna q = new Qna();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        conn = con.connect();
        if(conn!=null){
            System.out.println("MariaSQL 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_SELECT_BOARD);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                q.setQno(rs.getInt("qno"));
                q.setTitle(rs.getString("title"));
                q.setCid(rs.getString("cid"));
                q.setContent(rs.getString("content"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                q.setResdate(date);
                q.setLev(rs.getInt("lev"));
                q.setCnt(rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        updateCnt(no);
        return q;
    }

    public List<Qna> getComments(int no){
        List<Qna> qnaList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        conn = con.connect();
        if(conn!=null){
            System.out.println("MariaSQL 연결 성공");
        }

        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_SELECT_COMMENTS);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Qna q = new Qna();
                q.setQno(rs.getInt("qno"));
                q.setTitle(rs.getString("title"));
                q.setCid(rs.getString("cid"));
                q.setContent(rs.getString("content"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                q.setResdate(date);
                q.setLev(rs.getInt("lev"));
                q.setCnt(rs.getInt("cnt"));
                qnaList.add(q);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return qnaList;
    }

    public Qna getComment(int no){
        Qna q = new Qna();
        DBConnect con = new MariaDBCon();
        SimpleDateFormat ymd = new SimpleDateFormat("yy-MM-dd");

        conn = con.connect();
        if(conn!=null){
            System.out.println("MariaSQL 연결 성공");
        }

        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_SELECT_COMMENT);
            pstmt.setInt(1, no);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                q.setQno(rs.getInt("qno"));
                q.setTitle(rs.getString("title"));
                q.setCid(rs.getString("cid"));
                q.setContent(rs.getString("content"));

                Date d = ymd.parse(rs.getString("resdate"));  //날짜데이터로 변경
                String date = ymd.format(d);
                q.setResdate(date);
                q.setLev(rs.getInt("lev"));
                q.setCnt(rs.getInt("cnt"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return q;
    }
    
    // 조회수 갱신
    public void updateCnt(int no) {
        Qna q = new Qna();
        DBConnect con = new MariaDBCon();

        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_UPDATE_CNT);
            pstmt.setInt(1, no);
            int cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
    }
    

    public int addBoard(Qna q){
        int tot = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        Connection conn = null;
        PreparedStatement pstmt = null;

        // par 를 넣기전에 질문 게시글을 db에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.QNA_INSERT_BOARD;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, q.getTitle());
            pstmt.setString(2, q.getContent());
            pstmt.setString(3, q.getCid());
            pstmt.setInt(4, 0);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("질문 생성 완료");
                tot++;
            } else {
                System.out.println("질문 생성 실패");
            }
        } catch (SQLException e) {
            System.out.println("질문 생성: sql 에러");
        } catch (Exception e) {

        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }

        // 질문 게시글에 저장을 한 후 qno를 뽑아서 par에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.QNA_UPDATE_PAR;
            pstmt = conn.prepareStatement(sql);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("update 완료");
                tot++;
            } else {
                System.out.println("추가 실패");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }
        return tot;
    }

    public int addComment(Qna q){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        Connection conn = null;
        PreparedStatement pstmt = null;

        // par 를 넣기전에 질문 게시글을 db에 저장
        try {
            conn = con.connect();
            String sql = DBConnect.QNA_INSERT_COMMENT;
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, "댓글");
            pstmt.setString(2, q.getContent());
            pstmt.setString(3, q.getCid());
            pstmt.setInt(4, 1);
            pstmt.setInt(5, q.getQno());
            cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                System.out.println("질문 생성 완료");
            } else {
                System.out.println("질문 생성 실패");
            }
        } catch (SQLException e) {
            System.out.println("질문 생성: sql 에러");
        } catch (Exception e) {

        } finally {
            con.close(pstmt, conn); // db commit(저장)
        }
        return cnt;
    }

    public int updateQna(Qna q){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria updateNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_UPDATE);
            pstmt.setString(1, q.getTitle());
            pstmt.setString(2, q.getContent());
            pstmt.setInt(3, q.getQno());
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }

        return cnt;
    }

    public int deleteQna(int no){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("Maria deleteNotice 연결 성공");
        }
        try {
            pstmt = conn.prepareStatement(DBConnect.QNA_DELETE);
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
