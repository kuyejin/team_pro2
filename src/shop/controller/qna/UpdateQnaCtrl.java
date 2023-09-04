package shop.controller.qna;

import shop.dto.Notice;
import shop.dto.Qna;
import shop.model.NoticeDAO;
import shop.model.QnaDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/UpdateQna.do")
public class UpdateQnaCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "공지사항의 내용을 수정합니다.";
        int no = Integer.parseInt(request.getParameter("qno"));
        int lev = Integer.parseInt(request.getParameter("lev"));

        QnaDAO dao = new QnaDAO();
        Qna qna;
        if(lev == 0) {
            qna = dao.getBoard(no);
        } else {
            qna = dao.getComment(no);
        }

        request.setAttribute("msg", msg);
        request.setAttribute("qna", qna);

        RequestDispatcher view = request.getRequestDispatcher("/qna/updateQna.jsp");
        view.forward(request,response);
    }
}
