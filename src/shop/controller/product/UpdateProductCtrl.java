package shop.controller.product;


import shop.dto.Category;
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

@WebServlet("/UpdateProduct.do")
public class UpdateProductCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "상품 정보를 수정합니다.";
        int no = Integer.parseInt(request.getParameter("no"));

        ProductDAO dao = new ProductDAO();
        Product pro = dao.getProduct(no);
        List<Category> cateList = dao.getCategoryList();

        request.setAttribute("cateList", cateList);
        request.setAttribute("msg", msg);
        request.setAttribute("pro", pro);

        RequestDispatcher view = request.getRequestDispatcher("/product/updateProduct.jsp");
        view.forward(request,response);
    }
}
