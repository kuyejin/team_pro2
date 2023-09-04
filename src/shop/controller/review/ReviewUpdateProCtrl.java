package shop.controller.review;

import shop.dto.Review;
import shop.model.ReviewDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateReviewPro.do")
public class ReviewUpdateProCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("sid");
        String content = request.getParameter("content");
        int pno = Integer.parseInt(request.getParameter("par"));

        Review rv = new Review();
        rv.setCid(sid);
        rv.setContent(content);
        rv.setPar(pno);

        ReviewDAO dao = new ReviewDAO();
        int cnt = dao.updateReview(rv);
        if(cnt > 0) {
            response.sendRedirect("/pro02/Product.do?no="+pno);
        } else {
            response.sendRedirect("/pro02");
        }
    }
}
