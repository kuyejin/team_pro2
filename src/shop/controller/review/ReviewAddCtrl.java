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
import java.io.PrintWriter;

@WebServlet("/AddReview.do")
public class ReviewAddCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String sid = (String) session.getAttribute("sid");
        String content = request.getParameter("content");
        int pno = Integer.parseInt(request.getParameter("pno"));

        Review rv = new Review();
        rv.setCid(sid);
        rv.setContent(content);
        rv.setPar(pno);

        ReviewDAO dao = new ReviewDAO();
        boolean ck = dao.reviewCheck(sid, pno);
        if(ck) {
            int cnt = dao.addReview(rv);
            if(cnt > 0) {
                RequestDispatcher view = request.getRequestDispatcher("/Product.do?no="+pno);
                view.forward(request, response);
            } else {
                response.sendRedirect("/pro02");
            }
        } else {
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('후기를 이미 작성하였습니다.'); location.href='/pro02/Product.do?no="+pno+"';</script>");
            out.flush();
        }

    }
}
