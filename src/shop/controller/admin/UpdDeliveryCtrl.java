package shop.controller.admin;

import shop.dto.Delivery;
import shop.model.DeliveryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateDeliveryPost.do")
public class UpdDeliveryCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Delivery deli = new Delivery();
        DeliveryDAO dao = new DeliveryDAO();
        int dno = Integer.parseInt(request.getParameter("dno"));

        deli = dao.getDelivery(dno);
        request.setAttribute("deli", deli);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/updateDelivery.jsp");
        view.forward(request, response);
    }
}
