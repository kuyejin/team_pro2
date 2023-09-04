package shop.controller.payment;

import shop.dto.Payment;
import shop.model.DeliveryDAO;
import shop.model.PaymentDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/BuyPayment.do")
public class BuyPaymentCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sno = Integer.parseInt(request.getParameter("sno"));

        DeliveryDAO deliDAO = new DeliveryDAO();

        boolean ck = deliDAO.buyDelivery(sno);
        if(ck) {
            System.out.println("구매 확정");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('구매가 확정되었습니다.'); location.href='/pro02/PaymentList.do';</script>");
            out.flush();
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('오류가 발생했습니다.'); location.href='/pro02/PaymentList.do';</script>");
            out.flush();
        }
    }
}
