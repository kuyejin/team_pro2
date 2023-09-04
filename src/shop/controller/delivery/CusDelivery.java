package shop.controller.delivery;

import shop.dto.DeliveryInfoView;
import shop.model.DeliveryDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// 배송 중
@WebServlet("/CusDelivery.do")
public class CusDelivery extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sno = Integer.parseInt(request.getParameter("sno"));

        DeliveryDAO dao = new DeliveryDAO();
        DeliveryInfoView divl = new DeliveryInfoView();
        divl = dao.deliveryInfoViewList(sno);

        request.setAttribute("deli", divl);
        RequestDispatcher view = request.getRequestDispatcher("/delivery/cusDelivery.jsp");
        view.forward(request, response);
    }
}
