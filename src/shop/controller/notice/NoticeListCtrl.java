package shop.controller.notice;

import shop.dto.Notice;
import shop.model.NoticeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/NoticeList.do")
public class NoticeListCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "공지사항 목록을 출력합니다.");

        NoticeDAO dao = new NoticeDAO();
        List<Notice> notiList = dao.getNoticeList();
        request.setAttribute("notiList", notiList);

        RequestDispatcher view = request.getRequestDispatcher("/notice/noticeList.jsp");
        view.forward(request, response);
    }
}
