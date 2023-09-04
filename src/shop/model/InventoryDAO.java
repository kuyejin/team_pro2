package shop.model;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventoryDAO {

    static Connection conn = null;
    static PreparedStatement pstmt = null;
    static ResultSet rs = null;


    public List<Object> getInvetoryList() {
        List<Object> invList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();

        try {
            pstmt = conn.prepareStatement(DBConnect.INVENTORY_SUMMARY_SELECT);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Map<Object,Object> map = new HashMap<>();
                map.put("cate",rs.getString("c_no")); //카테고리코드
                map.put("cname",rs.getString("c_name")); //카테고리명
                map.put("totalamount",rs.getInt("i_amount")); //총재고수량
                map.put("saleamount",rs.getInt("s_amount")); //총판매량
                map.put("saleprice",rs.getInt("s_price")); //총판매가격
                System.out.println(map.toString());
                invList.add(map);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러 인벤토리 list 에러");
        } finally {
            con.close(rs, pstmt, conn);
        }
        return invList;
    }


    public List<Object>  getInvDetailList(String cate) {
        List<Object> invList = new ArrayList<>();
        DBConnect con = new MariaDBCon();
        conn = con.connect();
        System.out.println(cate);
        try {
            pstmt = conn.prepareStatement(DBConnect.INVENTORY_SELECT_CATE);
            pstmt.setString(1,cate);
            rs = pstmt.executeQuery();
            while(rs.next()) {
                Map<Object, Object> map = new HashMap<>();
                //map.put("cate",rs.getString("p.cate")); //카테고리코드
                map.put("pname",rs.getString("pname")); //상품명
                map.put("pamount",rs.getInt("i_amount")); //재고수량
                map.put("pprice",rs.getInt("price")); //상품가격
                map.put("totalamount",rs.getInt("s_amount")); //총판매수량
                map.put("totalprice",rs.getInt("s_cost")); //총판매수익

                invList.add(map);
            }
        } catch (SQLException e) {
            System.out.println("sql 에러" + e.getMessage());
        } finally {
            con.close(rs, pstmt, conn);
        }
        return invList;
    }




}