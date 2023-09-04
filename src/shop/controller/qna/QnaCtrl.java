package shop.controller.qna;

import shop.dto.Product;
import shop.dto.Qna;
import shop.dto.Review;
import shop.model.ProductDAO;
import shop.model.QnaDAO;
import shop.model.ReviewDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet("/GetQna.do")
public class QnaCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int qno = Integer.parseInt(request.getParameter("qno"));
        HttpSession session = request.getSession(true);
        String sid = (String) session.getAttribute("sid");

        QnaDAO dao = new QnaDAO();
        Qna board = dao.getBoard(qno);

        List<Qna> comment = new ArrayList<>();
        comment = dao.getComments(qno);

        request.setAttribute("board", board);
        if(comment != null) {
            request.setAttribute("comment", comment);
            System.out.println(comment.toString());
        }
        System.out.println(board.toString());

        if( sid != null && ( sid.equals("admin") || (sid.equals(board.getCid())))) {
            RequestDispatcher view = request.getRequestDispatcher("/qna/getQna.jsp");
            view.forward(request, response);
        } else {
            response.sendRedirect("/pro02/QnaList.do");
        }
    }
}
