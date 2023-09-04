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

@WebServlet("/JoinPro.do")
public class JoinProCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String birth = request.getParameter("birth");
        String address1 = request.getParameter("address1");
        String address2 = request.getParameter("address2");
        String postcode = request.getParameter("postcode");
        String msg = "회원가입 페이지";

        CustomDAO dao = new CustomDAO();
        System.out.printf("%s %s %s %s %s %s %s ", id, pw, name, tel, email, birth, (address1 + "<br>" + address2));

        boolean pass = dao.join(id, pw, name, tel, email, birth, (address1 + "<br>" + address2 + "<br>" + postcode));
        System.out.println(pass);

        HttpSession session = request.getSession();
        RequestDispatcher view = null;

        if(pass) {
            msg = "회원가입 성공";
//            response.sendRedirect("/pro02");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원가입 성공');</script>");
            out.println("<script> location.href= '/pro02'; </script>");
            out.flush();
        } else {
            msg = "회원가입 실패";
//            response.sendRedirect("/pro02");
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원가입 실패');</script>");
            out.println("<script> location.href= '/pro02/Join.do'; </script>");
            out.flush();
        }
    }
}
