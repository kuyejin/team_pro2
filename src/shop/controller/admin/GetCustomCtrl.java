package shop.controller.admin;

import shop.dto.Custom;
import shop.dto.Product;
import shop.dto.Review;
import shop.model.CustomDAO;
import shop.model.PaymentDAO;
import shop.model.ProductDAO;
import shop.model.ReviewDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/GetCustom.do")
public class GetCustomCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");


        CustomDAO dao = new CustomDAO();
        Custom cus = dao.getCustom(id);
        request.setAttribute("cus", cus);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/getCustom.jsp");
        view.forward(request, response);



    }
}
