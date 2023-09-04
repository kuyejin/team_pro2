package shop.controller.payment;

import shop.dto.Payment;
import shop.model.PaymentDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ReturnPayment.do")
public class ReturnPaymentCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int sno = Integer.parseInt(request.getParameter("sno"));

        PaymentDAO payDAO = new PaymentDAO();
        Payment pay = payDAO.getPayment(sno);

        int cnt = payDAO.returnPayment(sno, pay.getPno(), pay.getAmount(), pay.getCid());

        response.sendRedirect(request.getContextPath()+"/PaymentList.do");
    }
}
