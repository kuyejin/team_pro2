package shop.controller.qna;

import shop.dto.Notice;
import shop.dto.Qna;
import shop.model.NoticeDAO;
import shop.model.QnaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/UpdateQnaPro.do")
public class UpdateQnaProCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Qna q = new Qna();
        q.setQno(Integer.parseInt(request.getParameter("qno")));
        q.setTitle(request.getParameter("title"));
        q.setContent(request.getParameter("content"));

        QnaDAO dao = new QnaDAO();
        int a = dao.updateQna(q);

        PrintWriter out = response.getWriter();

        if(a>0){
            response.sendRedirect("/pro02/QnaList.do");
        } else {
            out.println("<script>history.go(-1);</script>");
        }
    }
}
