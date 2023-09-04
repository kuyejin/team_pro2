package shop.controller.product;

import shop.dto.Product;
import shop.model.CartDAO;
import shop.model.LikeDAO;
import shop.model.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/ProList.do")
public class ProListCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductDAO dao = new ProductDAO();
        List<Product> proList;

        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("sid");
        CartDAO cartDAO = new CartDAO();
        int cntCart = cartDAO.cntCart(sid);
        session.setAttribute("cntCart",cntCart);

        String catename="";
        String cate = request.getParameter("cate");
        System.out.println(cate);
        if(cate == null) {
            proList = dao.getProductList();
            System.out.println(proList.toString());
        } else if(cate.equals("초등")) {
            proList = dao.getCateProductListSchool("초등");
            catename ="초등 전체";
            System.out.println(proList.toString());
        } else if(cate.equals("중등")) {
            proList = dao.getCateProductListSchool("중등");
            catename ="중등 전체";
            System.out.println(proList.toString());
        } else if(cate.equals("고등")) {
            proList = dao.getCateProductListSchool("고등");
            catename ="고등 전체";
            System.out.println(proList.toString());
        }else if(cate.equals("기타")) {
            proList = dao.getCateProductListSchool("기타");
            catename ="기타 전체";
            System.out.println(proList.toString());
        }
        else {
            proList = dao.getCateProductList(cate);
            catename = dao.getCateName(cate);

        }


        //좋아요한 상품 표시 기능 처리 부분
        //세션의 아이디값 가져오기
        LikeDAO likeDAO = new LikeDAO();
        List<Integer> likedProductIds = likeDAO.getLikedProductsByUser(sid);
        request.setAttribute("likedProductIds", likedProductIds);



        request.setAttribute("catename",catename);

        request.setAttribute("proList",proList);
        request.setAttribute("cate",cate);


        System.out.println("상품목록 조회" + cate);
        RequestDispatcher view = request.getRequestDispatcher("/product/proList.jsp");
        view.forward(request, response);
    }
}
