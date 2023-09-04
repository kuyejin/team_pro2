package shop.controller.like;

import shop.dto.CartVO;
import shop.dto.Like;
import shop.model.CartDAO;
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

@WebServlet("/LikeList.do")
public class LikeListCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "장바구니 목록을 출력합니다.");

        HttpSession session = request.getSession();
        String cid = (String) session.getAttribute("sid");

        LikeDAO dao = new LikeDAO();
        List<Like> likeList = dao.getByIdLikeList(cid);

        request.setAttribute("likeList", likeList);


        RequestDispatcher view = request.getRequestDispatcher("/like/likeList.jsp");
        view.forward(request, response);
    }
}
