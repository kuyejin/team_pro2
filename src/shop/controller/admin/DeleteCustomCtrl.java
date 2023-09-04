package shop.controller.admin;

import shop.dto.Custom;
import shop.model.CustomDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/DeleteCustom.do")
public class DeleteCustomCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        CustomDAO dao = new CustomDAO();
        int cnt = dao.deleteCustom(id);
        if(cnt > 0) {
            System.out.println("회원삭제 완료");

            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 강퇴가 완료되었습니다.');</script>");
            out.println("<script> location.href= '/pro02/CustomList.do'; </script>");
            out.flush();

        }else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('회원 강퇴에 실패하였습니다'); location.href='/pro02/CustomList.do';</script>");
            out.flush();
            //response.sendRedirect(home+"/AddProduct.do");

        }
        
    }
}
