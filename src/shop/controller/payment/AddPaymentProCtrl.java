package shop.controller.payment;

import shop.dto.Delivery;
import shop.dto.Payment;
import shop.dto.Serve;
import shop.model.CartDAO;
import shop.model.DeliveryDAO;
import shop.model.PaymentDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddPaymentPro.do")
public class AddPaymentProCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //결제 처리(PaymentDAO.addPayment(pay))
        Payment pay = new Payment();
        pay.setCid(request.getParameter("cid"));
        pay.setPno(Integer.parseInt(request.getParameter("pno")));
        pay.setAmount(Integer.parseInt(request.getParameter("amount")));
        pay.setPmethod(request.getParameter("pmethod"));
        pay.setPcom(request.getParameter("pcom2"));
        pay.setCnum(request.getParameter("cnum"));
        pay.setPayprice(Integer.parseInt(request.getParameter("payAmount")));

        PaymentDAO payDAO = new PaymentDAO();
        int cnt1 = payDAO.addPayment(pay);

        //출고 처리(PaymentDAO.addServe(serv))
        Serve serv = new Serve();
        serv.setPno(Integer.parseInt(request.getParameter("pno")));
        serv.setAmount(Integer.parseInt(request.getParameter("amount")));
        int sprice = (int) Double.parseDouble(request.getParameter("sprice"));
        serv.setSprice(sprice);

        int cnt2 = payDAO.addServe(serv);

        //배송 등록(DeliveryDAO.addDelivery(del))
        Delivery del = new Delivery();
        del.setSno(payDAO.getSno());
        del.setCid(request.getParameter("cid"));
        del.setDaddr(request.getParameter("address1")+"<br>"+request.getParameter("address2")+"<br>"+request.getParameter("postcode"));
        del.setCustel(request.getParameter("custel"));

        DeliveryDAO deliDAO = new DeliveryDAO();
        int cnt3 = deliDAO.addDelivery(del);

        //장바구니에서 결제한 정보라면 (CartDAO.delCart(cartno));
        CartDAO cartDAO = new CartDAO();
        String from = request.getParameter("from");
        int cartno = 0;
        int cnt4 = 0;
        if(from.equals("cart")){
            cartno = Integer.parseInt(request.getParameter("cartno"));
            cnt4 = cartDAO.delCart(cartno);
        }

        int pno = Integer.parseInt(request.getParameter("pno"));

        if(cnt1 > 0 && cnt2 > 0 && cnt3 > 0){
            response.sendRedirect(request.getContextPath()+"/ProList.do");
        } else {
            response.sendRedirect(request.getContextPath()+"/AddPayment.do?pno="+pno);
        }

    }
}
