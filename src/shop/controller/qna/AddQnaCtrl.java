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
import java.util.List;

@WebServlet("/AddQna.do")
public class AddQnaCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "공지사항 목록을 출력합니다.");

        RequestDispatcher view = request.getRequestDispatcher("/qna/addQna.jsp");
        view.forward(request, response);
    }
}
