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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/AddQnaPro.do")
public class AddQnaProCtrl extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Qna q = new Qna();
        //noti.setTitle(title);
        q.setTitle(request.getParameter("title"));
        q.setContent(request.getParameter("content"));

        HttpSession session = request.getSession(true);
        q.setCid((String) session.getAttribute("sid"));
    
        // 아웃객체 떄문에 한글 오류 발생
        // PrintWriter out = response.getWriter();

        QnaDAO dao = new QnaDAO();
        int a = dao.addBoard(q);
        if(a>1) {
            System.out.println("qna 작성 완료");
            response.sendRedirect("/pro02");
        } else {
            response.sendRedirect("/AddNotice.do");
            // out.println("<script>history.go(-1);</script>");
        }
    }
}
