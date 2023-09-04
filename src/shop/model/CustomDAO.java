package shop.model;

import shop.dto.Custom;
import shop.util.AES256;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomDAO {
    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;
    static String key = "%02x";

    public List<Custom> getCustomList() {
        List<Custom> cusList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_SELECT_ALL);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Custom custom = new Custom();
                custom.setId(rs.getString("id"));
                custom.setPw(rs.getString("pw"));
                custom.setName(rs.getString("name"));
                custom.setPoint(rs.getInt("point"));
                custom.setGrade(rs.getString("grade"));
                custom.setTel(rs.getString("tel"));
                custom.setEmail(rs.getString("email"));
                custom.setBirth(rs.getString("birth"));
                custom.setRegdate(rs.getString("regdate"));
                cusList.add(custom);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } finally {
            con.close(rs, pstmt, conn);
        }
        return cusList;
    }

    public Custom getCustom(String id) {
        Custom cus = new Custom();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        String qpw = "";
        try {
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_SELECT_ONE);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(rs.next()) {
                cus.setId(rs.getString("id"));

                try {
                    qpw = AES256.decryptAES256(rs.getString("pw"), key);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }

                cus.setPw(qpw);
                cus.setName(rs.getString("name"));
                cus.setPoint(rs.getInt("point"));
                cus.setGrade(rs.getString("grade"));
                cus.setTel(rs.getString("tel"));
                cus.setEmail(rs.getString("email"));
                cus.setBirth(rs.getString("birth"));
                cus.setRegdate(rs.getString("regdate"));
                cus.setAddress(rs.getString("address"));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return cus;
    }

    public boolean login(String id, String pw) {
        boolean pass = false;
        DBConnect con = new MariaDBCon();
        String qpw = "";

        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_SELECT_LOG);
            pstmt.setString(1, id);
            rs = pstmt.executeQuery();
            if(rs.next()){
                try {
                    qpw = AES256.decryptAES256(rs.getString("pw"), key);
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                System.out.println("복호화 " + qpw);
                if(pw.equals(qpw)){
                    pass = true;
                } else {
                    pass = false;
                }
            } else {
                pass = false;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return pass;
    }

    public boolean join(String id, String pw, String name, String tel, String email, String birth, String address) {
        boolean pass = false;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        String qpw = "";
        try {
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_INSERT);
            pstmt.setString(1, id);
            try {
                qpw = AES256.encryptAES256(pw, key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            pstmt.setString(2, qpw);
            pstmt.setString(3, name);
            pstmt.setString(4, tel);
            pstmt.setString(5, email);
            pstmt.setString(6, birth);
            pstmt.setString(7, address);
            int cnt = pstmt.executeUpdate();
            if(cnt > 0) {
                pass = true;
            }
        } catch (SQLException e) {
            System.out.println("sql 에러");
        } finally {
            con.close(pstmt, conn);
        }
        return pass;
    }

    public boolean idCheck(String id) {
        Custom custom = new Custom();
        DBConnect con = new MariaDBCon();
        boolean pass = false;
        conn = con.connect();
        try {
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_SELECT_ONE);
            pstmt.setString(1,id);
            rs = pstmt.executeQuery();
            if(!rs.next()) {
                pass = true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(rs, pstmt, conn);
        }
        return pass;
    }



    public int modifyCustom(Custom cus) {
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        String qpw = "";
        try {
            conn = con.connect();
            pstmt = conn.prepareStatement(DBConnect.CUSTOM_UPDATE);
            try {
                qpw = AES256.encryptAES256(cus.getPw(), key);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            System.out.println("암호화 : "+qpw);
            pstmt.setString(1, qpw);
            pstmt.setString(2, cus.getTel());
            pstmt.setString(3, cus.getEmail());
            pstmt.setString(4, cus.getAddress());
            pstmt.setString(5, cus.getBirth());
            pstmt.setString(6, cus.getId());

            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }

    public int deleteCustom(String id){
        int cnt = 0;
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        if(conn!=null){
            System.out.println("SQL 연결 성공");
        }

        String sql = "delete from custom where id=?";
        try {
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            cnt = pstmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            con.close(pstmt, conn);
        }
        return cnt;
    }
}
