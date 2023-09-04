package shop.controller.like;

import shop.dto.Like;
import shop.model.LikeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/DelLike.do")
public class DelLikeCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pno = request.getParameter("pno");
        String id = request.getParameter("sid");
        LikeDAO dao = new LikeDAO();
        dao.removeLike(id, pno);

        response.sendRedirect("/pro02/LikeList.do");
    }
}
