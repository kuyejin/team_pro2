package shop.controller.review;

import shop.dto.Notice;
import shop.dto.Receive;
import shop.dto.Review;
import shop.model.NoticeDAO;
import shop.model.ReviewDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/UpdateReview.do")
public class ReviewUpdateCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "리뷰를 수정합니다.";
        int no = Integer.parseInt(request.getParameter("par"));
        String cid = request.getParameter("cid");

        ReviewDAO dao = new ReviewDAO();
        Review noti = dao.getReview(no, cid);

        request.setAttribute("msg", msg);
        request.setAttribute("noti", noti);

        RequestDispatcher view = request.getRequestDispatcher("/product/updateReview.jsp");
        view.forward(request,response);
    }
}
