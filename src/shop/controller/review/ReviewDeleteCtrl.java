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

@WebServlet("/DeleteReview.do")
public class ReviewDeleteCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sid = request.getParameter("cid");
        int pno = Integer.parseInt(request.getParameter("par"));

        Review rv = new Review();
        rv.setCid(sid);
        rv.setPar(pno);

        ReviewDAO dao = new ReviewDAO();
        int cnt = dao.deleteReview(rv);
        if(cnt > 0) {
            RequestDispatcher view = request.getRequestDispatcher("/Product.do?no="+pno);
            view.forward(request, response);
        } else {
            response.sendRedirect("/pro02");
        }
    }
}
