package shop.controller.inventory;

import shop.model.InventoryDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/InventoryPro.do")
public class InventoryProCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cate = request.getParameter("cate");

        InventoryDAO invdao = new InventoryDAO();
        List invList = invdao.getInvDetailList(cate);

        request.setAttribute("invList", invList);
        System.out.println(invList.toString());

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/invDetailList.jsp");
        view.forward(request, response);




    }
}
