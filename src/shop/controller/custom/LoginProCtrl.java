package shop.controller.custom;

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
import java.io.PrintWriter;

@WebServlet("/LoginPro.do")
public class LoginProCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String msg = "로그인 페이지";

        CustomDAO dao = new CustomDAO();
        boolean pass = dao.login(id, pw);

        HttpSession session = request.getSession();
        RequestDispatcher view = null;
        if(pass) {
            msg = "로그인 성공";
            session.setAttribute("sid", id);
            request.setAttribute("msg", msg);
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 성공');</script>");
            out.println("<script> location.href= '/pro02'; </script>");
            out.flush();
//            response.sendRedirect("/pro02");
//            response.setContentType("text/html;charset=UTF-8");
//            response.setCharacterEncoding("UTF-8");
//            view = request.getRequestDispatcher("/pro02");
//            view.forward(request, response);
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('로그인 실패');</script>");
            out.println("<script> location.href= '/pro02/Login.do'; </script>");
            out.flush();
        }
    }
}
