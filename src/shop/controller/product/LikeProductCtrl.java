package shop.controller.product;

import shop.model.LikeDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/ProductLike.do")
public class LikeProductCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pno = request.getParameter("pno");
        String id = request.getParameter("sid");
        LikeDAO dao = new LikeDAO();

        //좋아요 눌렀을때의 동작
        try {
            //좋아요 눌렀는지 유무 확인하고
            //눌렀으면 좋아요 취소
            if (dao.checkLiked(id, pno)) {
                dao.removeLike(id, pno);
                response.getWriter().write("unliked");
                request.setAttribute("isLiked",false);
            } else {
                //좋아요 추가
                dao.addLike(id, pno);
                response.getWriter().write("liked");
                request.setAttribute("isLiked",true);
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.getWriter().write("error");
        }
    }
}





