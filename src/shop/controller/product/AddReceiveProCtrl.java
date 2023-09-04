package shop.controller.product;

import shop.dto.Receive;
import shop.model.ProductDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddReceivePro.do")
public class AddReceiveProCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletContext application = request.getServletContext();
        String home = application.getContextPath();

        Receive rec = new Receive();
        rec.setPno(Integer.parseInt(request.getParameter("no")));
        rec.setAmount(Integer.parseInt(request.getParameter("amount")));
        rec.setRprice(Integer.parseInt(request.getParameter("price")));

        ProductDAO dao = new ProductDAO();
        int cnt = dao.addReceive(rec);
        if(cnt>0){
            response.sendRedirect(home+"/ProList.do");
        } else {
            response.sendRedirect(home+"/AddReceive.do");
        }
    }
}
