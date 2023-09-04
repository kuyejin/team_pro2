package shop.controller.qna;

import shop.model.NoticeDAO;
import shop.model.QnaDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteQna.do")
public class DeleteQnaCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("qno"));

        QnaDAO dao = new QnaDAO();
        int a = dao.deleteQna(no);

        if(a>0){
            response.sendRedirect("/pro02/QnaList.do");
        } else {
            response.sendRedirect("/pro02");
        }
    }
}
