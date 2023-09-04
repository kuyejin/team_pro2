package shop.controller.product;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddProduct.do")
public class AddProductCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "관리자의 상품 등록폼이 로딩되었습니다.";

        request.setAttribute("msg", msg);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/addProduct.jsp");
        view.forward(request, response);
    }
}
