package shop.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public interface DBConnect {
    // region notice
    final static String NOTICE_SELECT_ALL = "select * from notice";
    final static String NOTICE_SELECT_ONE = "select * from notice where no=?";
    final static String NOTICE_INSERT = "insert into notice values (default,?,?,default,default)";
    final static String NOTICE_UPDATE = "update notice set title=?, content=? where no=?";
    final static String NOTICE_UPDATE_CNT = "update notice set visited=visited+1 where no=?";
    final static String NOTICE_DELETE = "delete from notice where no=?";
    // endregion

    final static String FAQ_SELECT_ALL = "select * from faq";
    final static String FAQ_SELECT_ONE = "select * from faq where fno=?";
    final static String FAQ_INSERT = "insert into faq(question, answer) values (?, ?)";
    final static String FAQ_UPDATE = "update faq set question=?, answer=? where fno=?";
    final static String FAQ_UPDATE_CNT = "update faq set cnt=cnt+1 where fno=?";
    final static String FAQ_DELETE = "delete from faq where no=?";



    // region customer
    final static String CUSTOM_SELECT_ALL = "select * from custom order by regdate desc";
    final static String CUSTOM_SELECT_ONE = "select * from custom where id=?";
    final static String CUSTOM_INSERT = "insert into custom values(?, ?, ?, default, default, ?, ?, ?, default, ?)";
    final static String CUSTOM_UPDATE = "update custom set pw=?, tel=?, email=?, address=?,birth=? where id=?";
    final static String CUSTOM_DELETE = "delete from custom where id=?";
    final static String CUSTOM_SELECT_LOG = "select * from custom where id=?";
    // endregion
    
    // region file
    final static String FILE_SELECT_ALL = "select * from file";
    final static String FILE_INSERT = "insert into file values (?,?,?,?)";
    final static String FILE_DELETE = "DELETE FROM file where fname = ?";
    // endregion

    // region shop

    // product
    final static String PRODUCT_SELECT_ALL = "SELECT * FROM product ORDER BY NO"; // 상품 조회
    final static String PRODUCT_SELECT_CATE = "SELECT p.*, c.cname FROM product p JOIN category c ON p.cate = c.cno where cate=? ORDER BY NO";
    final static String PRODUCT_SELECT_ONE =  "SELECT p.*, c.cname FROM product p JOIN category c ON p.cate = c.cno where no=?"; // 상품 상세 조회
    final static String PRODUCT_SELECT_RECENT = "SELECT * FROM product ORDER BY NO DESC LIMIT 5"; // 최근 상품 조회
    final static String PRODUCT_SELECT_NEW = "SELECT p.*, c.cname FROM product p JOIN category c ON p.cate = c.cno ORDER BY NO desc limit 4";
    final static String PRODUCT_SELECT_BEST = "SELECT p.*, c.cname FROM product p JOIN category c ON p.cate = c.cno where no in (select pno from payment group by pno order by sum(amount) desc )limit 4"; // 제일 잘나가는 상품 조회
    final static String PRODUCT_INSERT = "INSERT INTO product VALUES(DEFAULT, ?, '', ?, ?, ?, ?, ?, ?, ?, DEFAULT)";
    final static String PRODUCT_INSERT_CATENO = "update product set cateno = concat(cate, no) where no in (select no from product order by resdate desc)";
    final static String PRODUCT_UPDATE = "update product set pname=?, pcomment=?, plist=?, price=?, cate=? where no=?"; // 테스트! 상품정보수정
    final static String PRODUCT_UPDATE_CATENO= "UPDATE product SET cateno = CONCAT(cate, NO) WHERE NO=?";
    final static String PRODUCT_DELETE = "delete from product where no=?";

    // 얘가 문제
    final static String PRODUCT_SELECT_CATE_SCHOOL = "SELECT p.*, c.cname FROM product p JOIN category c ON p.cate = c.cno  WHERE cate IN (?,?,?,?) ORDER BY no";
    final static String PRODUCT_UPDATE_prono = "UPDATE product SET cateno = CONCAT(cate, NO) WHERE NO=?";
    final static String PRODUCT_INSERT_UPDATE = "update product set cateno = concat(cate, no) where no in (select no from product order by resdate desc limit 1)";


    // qna
    final static String QNA_SELECT_ALL = "select * from qna where lev=0 order by resdate desc";
    final static String QNA_SELECT_BOARD = "select * from qna where par=? and lev=0";
    final static String QNA_SELECT_COMMENTS = "select * from qna where par=? and lev=1 order by resdate desc";
    final static String QNA_SELECT_COMMENT = "select * from qna where qno=? and lev=1";
    final static String QNA_INSERT_BOARD = "INSERT INTO qna(title, content, cid, par) VALUES(?, ?, ?, ?)";
    final static String QNA_INSERT_COMMENT = "INSERT INTO qna(title, content, cid, lev, par) VALUES(?, ?, ?, ?, ?)";
    final static String QNA_UPDATE_PAR = "update qna set par=qno where par=0 and lev=0";
    final static String QNA_UPDATE_CNT = "update qna set cnt=cnt+1 where qno=?";
    final static String QNA_UPDATE = "update qna set title=?, content=? where qno=?";
    final static String QNA_DELETE = "delete from qna where qno=?";

    //review
    final static String REVIEW_SELECT_ALL_PAR = "select * from review where par=?";
    final static String REVIEW_SELECT_ALL = "select * from review";
    final static String REVIEW_SELECT_ALL_CID = "select * from review where cid=?";
    final static String REVIEW_SELECT_ONE = "select * from review where par=? and cid=?";
    final static String REVIEW_SELECT_CK = "select * from review where par=? and cid=?";
    final static String REVIEW_INSERT = "insert into review values(default, ?, ?, default, ?)";
    final static String REVIEW_UPDATE = "update review set content=? where cid=? and par=?";
    final static String REVIEW_DELETE = "delete from review where cid=? and par=?";
    
    
    //입고처리패턴
    final static String PRODUCT_RECEIVE = "insert into receive values (default, ?, ?, ?, default)";
    final static String CATEGORY_LOAD = "SELECT * FROM CATEGORY";


    //출고 처리 패턴
    final static String SERVE_PAYMENT = "insert into payment values (default, ?, ?, ?, ?, ?, ?, ?, '')";
    final static String SERVE_INSERT = "insert into serve values(default, ?, ?, ?, default)";
    final static String DELIVERY_INSERT = "insert into delivery values (default, ?, ?, ?, ?, '','',default,default,'','')";
    final static String CART_DELETE = "delete from cart where cartno=?";
    final static String GET_SNO = "select sno from payment order by sno desc limit 1";

    //반품 처리 패턴
    final static String RETURN_PAYMENT = "delete from payment where sno=?";
    final static String RETURN_RECEIVE = "insert into receive values (default, ?, ?, ?, default)";
    final static String RETURN_SERVE = "delete from serve where sno=?";
    final static String RETURN_CART = "insert into cart values (default, ?, ?, ?)";
    final static String RETURN_DELEVERY = "delete from delivery where sno=?";

    //배송 등록 처리
    final static String DELIVERY_PRO = "update delivery set pcom=?, ptel=?, pstate=?, rdate=?, bcode=? where dno=?";
    
    // 배송 조회
    final static String DELIVERY_SELECT_ALL = "select * from delivery where pstate=?";
    final static String DELIVERY_SELECT_ONE = "select * from delivery where dno=?";
    final static String DELIVERY_SELECT_BUY = "select * from delivery_info where pno=? and cid=? and pstate=3";
    final static String DELIVERY_SELECT_SNO = "select * from delivery where sno=?";
    final static String DELIVERY_BUY ="update delivery set pstate=3 where sno=?";

    // 배송 뷰 조회
    final static String DELIVERY_VIEW = "select * from delivery_info where sno=?";

    //재고 조회
    final static String INVENTORY_SUMMARY_SELECT ="select * from InventorySummary";

    final String INVENTORY_SELECT_CATE = "select * from inven_sales where cate=?";

    final static String INVENTORY_SELECT_ONE = "select * from inventory where pno=?";

    //입고 처리
    final static String RECEIVE_INSERT = "insert into receive values (default, ?, ?, ?, default)";
    
    // 입고 수량 갱신
    final static String RECEIVE_AMOUNT_UPDATE = "update receive set amount=amount+? where pno=?";

    //장바구니 기능
    final static String CART_SELECT_FIND = "select * from cart where cid=? and pno=?";
    final static String CART_INSERT = "insert into cart values (default,?,?,?)";
    final static String CART_SELECT_CID = "select * from cart where cid=?";
    final static String CART_COUNT = "select COUNT(cid) from cart where cid=?"; //장바구니 내 상품 카운트
    
    // 찜기능
    final static String LIKE_SELECT_CID = "select * from likes where userid=?";

    //결제 정보
    final static String PAYMENT_SELECT_LIST ="select * from payment order by sno desc";
    final static String PAYMENT_SELECT_ONE ="select * from payment where sno=?";
    final static String PAYMENT_SELECT_CID ="select * from payment where cid=?";
    final static String PAYMENT_SELECT_CID_PNO ="select * from payment where cid=? and pno=?";

    // 판매 수익 정보
    final static String SALES_SELECT_ALL = "select * from sales";

    public Connection connect();
    public void close(PreparedStatement pstmt, Connection conn);
    public void close(ResultSet rs, PreparedStatement pstmt, Connection conn);
}
