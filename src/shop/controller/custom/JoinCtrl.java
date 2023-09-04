package shop.controller.custom;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/Join.do")
public class JoinCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "회원가입 하시기 바랍니다.");
        RequestDispatcher view = request.getRequestDispatcher("/custom/join.jsp");
        view.forward(request, response);
    }
}
