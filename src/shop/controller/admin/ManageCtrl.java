package shop.controller.admin;

import shop.dto.Custom;
import shop.model.CustomDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/Manage.do")
public class ManageCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        CustomDAO dao = new CustomDAO();
        List<Custom> customList = new ArrayList<>();
        customList = dao.getCustomList();

        request.setAttribute("cusList", customList);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/index.jsp");
        view.forward(request, response);
    }
}
