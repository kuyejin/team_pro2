package shop.controller.admin;

import shop.dto.Custom;
import shop.dto.Payment;
import shop.dto.Sales;
import shop.model.CustomDAO;
import shop.model.PaymentDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Sales.do")
public class SalesCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        PaymentDAO dao = new PaymentDAO();
        List<Sales> salesList = new ArrayList<>();
        salesList = dao.getSales();

        request.setAttribute("salesList", salesList);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/sales.jsp");
        view.forward(request, response);
    }
}
