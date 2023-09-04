package shop.controller;

import shop.dto.Product;
import shop.model.CartDAO;
import shop.model.ProductDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

public class Main extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        String realPath = request.getSession().getServletContext().getRealPath("/");

        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("sid");
        CartDAO cartDAO = new CartDAO();
        int cntCart = cartDAO.cntCart(sid);

        request.setAttribute("cntCart",cntCart);

        ProductDAO dao = new ProductDAO();
        List<Product> bestList = dao.getProductListBest();
        List<Product> newList = dao.getProductListNew();

        request.setAttribute("bestList", bestList);
        request.setAttribute("newList", newList);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/index.jsp");
        view.forward(request, response);
    }

}
