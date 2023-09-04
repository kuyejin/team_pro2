package shop.controller.admin;

import shop.dto.Custom;
import shop.dto.Notice;
import shop.model.CustomDAO;
import shop.model.NoticeDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/CustomList.do")
public class CustomListCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "회원 목록을 출력합니다.");

        CustomDAO dao = new CustomDAO();
        List<Custom> cusList = dao.getCustomList();
        request.setAttribute("cusList", cusList);

        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/customList.jsp");
        view.forward(request, response);
    }
}
