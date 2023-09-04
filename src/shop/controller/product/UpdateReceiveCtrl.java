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

@WebServlet("/UpdateReceive.do")
public class UpdateReceiveCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "상품 업데이트 페이지 실행";
        request.setAttribute("msg", msg);

        ProductDAO dao = new ProductDAO();
        List<Product> proList = dao.getProductList();

        request.setAttribute("proList", proList);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/updateReceive.jsp");
        view.forward(request, response);
    }
}
