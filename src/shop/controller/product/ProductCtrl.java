package shop.controller.product;

import shop.dto.Product;
import shop.dto.Review;
import shop.model.*;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/Product.do")
public class ProductCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));

        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("sid");
        CartDAO cartDAO = new CartDAO();
        int cntCart = cartDAO.cntCart(sid);
        session.setAttribute("cntCart",cntCart);

        // 상품 상세 내용 정보 추출
        ProductDAO dao = new ProductDAO();
        Product pro = dao.getProduct(no);
        System.out.println(pro.toString());
        request.setAttribute("pro", pro);
        
        // 리뷰 목록 추출
        ReviewDAO dao2 = new ReviewDAO();
        List<Review> revList = dao2.getReviewList(no);
//        System.out.println(revList.toString());
        for (Review r:revList) {
            r.toString();
        }
        System.out.println("revList 보내기");
        request.setAttribute("revList", revList);
        
        // 해당 상품 구매자 확인, 구매자 인 경우 리뷰 작성 가능
        PaymentDAO dao3 = new PaymentDAO();
        int check1 = dao3.getPaymentBuyCustom(no, sid);

        DeliveryDAO dao4 = new DeliveryDAO();
        int check2 = dao4.getDelivery(no, sid);

        int check = check1 + check2;
        request.setAttribute("check", check);

        //좋아요한 상품 표시 기능 처리 부분
        LikeDAO likeDAO = new LikeDAO();
        List<Integer> likedProductIds = likeDAO.getLikedProductsByUser(sid);
        request.setAttribute("likedProductIds", likedProductIds);


        RequestDispatcher view = request.getRequestDispatcher("/product/getProduct.jsp");
        view.forward(request, response);
    }
}
