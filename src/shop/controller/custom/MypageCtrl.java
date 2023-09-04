package shop.controller.custom;

import shop.dto.Custom;
import shop.model.CustomDAO;
import shop.util.AES256;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/Mypage.do")
public class MypageCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("sid");

        CustomDAO dao = new CustomDAO();
        Custom cus = dao.getCustom(id);

        System.out.println(cus.toString());
        request.setAttribute("cus", cus);

        RequestDispatcher view = request.getRequestDispatcher("/custom/mypage.jsp");
        view.forward(request, response);
    }
}
