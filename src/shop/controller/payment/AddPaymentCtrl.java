package shop.controller.payment;

import shop.dto.Custom;
import shop.dto.Product;
import shop.model.CustomDAO;
import shop.model.ProductDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddPayment.do")
public class AddPaymentCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");

        int pno = Integer.parseInt(request.getParameter("pno"));
        PrintWriter out = response.getWriter();
        HttpSession session = request.getSession();
        String cid = (String) session.getAttribute("sid");

        String from;
        String cartno;

        if(request.getParameter("from") != null) {
            from = request.getParameter("from");
            request.setAttribute("from", from);
        } else {

        }

        if(request.getParameter("cartno") != null) {
            cartno = request.getParameter("cartno");
            request.setAttribute("cartno", cartno);
        } else {

        }

        ProductDAO dao = new ProductDAO();
        Product pro = dao.getProduct(pno);
        int amount = dao.getAmount(pno);

        CustomDAO cusDao = new CustomDAO();
        Custom cus = cusDao.getCustom(cid);

        request.setAttribute("pro", pro);
        request.setAttribute("amount", amount);
        request.setAttribute("cus", cus);
        System.out.println("amount: " + amount);
        if(pro!=null) {
            RequestDispatcher view = request.getRequestDispatcher("/payment/addPayment.jsp");
            view.forward(request, response);
        } else {
            //request.sendRedirect("/AddNotice.do");
            out.println("<script>history.go(-1);</script>");
        }
    }
}
