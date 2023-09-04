package shop.controller.admin;

import shop.dto.Delivery;
import shop.model.DeliveryDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// 배송 시작
@WebServlet("/AdminDeliveryList.do")
public class DeliveryListCtrl extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "배송 시작 하기";

        List<Delivery> deliveryList = new ArrayList<>();
        DeliveryDAO dao = new DeliveryDAO();
        int pstate = Integer.parseInt(request.getParameter("pstate"));
        deliveryList = dao.getDeliveryList(pstate);
        
        String title = "배송관리";
        
        request.setAttribute("deliveryList", deliveryList);
        if(pstate == 0) {
            title = "출고처리";
        } else if (pstate == 1) {
            title = "배송중";
        } else if (pstate == 2) {
            title = "배송완료";
        } else {
            title = "구매완료";
        }
        System.out.println(title);
        request.setAttribute("title", title);
        request.setAttribute("msg", msg);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/DeliveryList.jsp");
        view.forward(request, response);
    }
}
