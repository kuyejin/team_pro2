package shop.controller.product;

import shop.dto.Product;
import shop.model.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/AddReceive.do")
public class AddReceiveCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "관리자의 상품 입고 폼이 로딩되었습니다.";

        request.setAttribute("msg", msg);

        ProductDAO dao = new ProductDAO();
        List<Product> proList = dao.getProductList();

        request.setAttribute("proList", proList);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/addReceive.jsp");
        view.forward(request, response);
    }
}