package shop.controller.qna;

import shop.dto.Qna;
import shop.model.QnaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/AddComment.do")
public class AddCommentProCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "공지사항 목록을 출력합니다.");
        String content = request.getParameter("content");
        int qno = Integer.parseInt(request.getParameter("qno"));
        String cid = request.getParameter("id");

        Qna q = new Qna();
        QnaDAO dao = new QnaDAO();

        q.setContent(content);
        q.setQno(qno);
        q.setCid(cid);

        int cnt = dao.addComment(q);
        if(cnt > 0) {
            RequestDispatcher view = request.getRequestDispatcher("/pro02/QnaList.do");
            view.forward(request, response);
        } else {
            response.sendRedirect("/pro02/QnaList.do");
        }
    }
}
