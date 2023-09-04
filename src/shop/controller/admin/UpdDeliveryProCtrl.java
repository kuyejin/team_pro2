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

@WebServlet("/UpdateDeliveryPostPro.do")
public class UpdDeliveryProCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int pstate = Integer.parseInt(request.getParameter("pstate"));
        int dno = Integer.parseInt(request.getParameter("dno"));
        String pcom = request.getParameter("pcom");
        String ptel = request.getParameter("ptel");
        String bcode = request.getParameter("bcode");
        String rdate = request.getParameter("rdate");

        Delivery deli = new Delivery();
        deli.setPcom(pcom);
        deli.setPtel(ptel);
        deli.setPstate(pstate);
        deli.setBcode(bcode);
        deli.setDno(dno);
        deli.setRdate(rdate);

        DeliveryDAO dao = new DeliveryDAO();
        int cnt = dao.updateDelivery(deli);
        if(cnt > 0) {
            System.out.println("배송 정보 수정 완료");
        } else {
            System.out.println("배송 정보 수정 실패");
        }
        response.sendRedirect("/pro02/AdminDeliveryList.do?pstate="+pstate);
//        RequestDispatcher view = request.getRequestDispatcher("/pro02/AdminDeliveryList.do?pstate"+pstate);
//        view.forward(request, response);
    }
}
