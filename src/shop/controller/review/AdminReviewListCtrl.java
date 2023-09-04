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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/AdminReviewList.do")
public class AdminReviewListCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        List<Review> rvList = new ArrayList<>();

        ReviewDAO dao = new ReviewDAO();
        rvList = dao.getReviewList();
        request.setAttribute("rvList", rvList);
        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/adminRevList.jsp");
        view.forward(request, response);
    }
}
