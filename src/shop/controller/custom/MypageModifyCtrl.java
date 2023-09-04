package shop.controller.custom;

import shop.dto.Custom;
import shop.model.CustomDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MypageModify.do")
public class MypageModifyCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        HttpSession session = request.getSession();
        String id = request.getParameter("sid");

        CustomDAO dao = new CustomDAO();
        Custom cus = dao.getCustom(id);

        System.out.println(cus.toString());
        request.setAttribute("cus", cus);

        RequestDispatcher view = request.getRequestDispatcher("/custom/mypageModify.jsp");
        view.forward(request, response);
    }
}
