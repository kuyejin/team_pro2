package shop.controller.custom;

import shop.dto.Custom;
import shop.model.CustomDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/MypageModifyPro.do")
public class MypageModifyProCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String id = request.getParameter("id");
        String pw = request.getParameter("pw");
        String pw2 = request.getParameter("pw2");
        String birth = request.getParameter("birth");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String address = request.getParameter("address1") + "<br>" + request.getParameter("address2") +  "<br>" + request.getParameter("postcode");

        int cnt = 0;

        // 기존 비밀번호 확인을 위해 데이터를 가져옴
        CustomDAO dao = new CustomDAO();
        Custom cus = dao.getCustom(id);

        if(cus.getPw().equals(pw)) {
            cus.setPw(pw2);
            cus.setBirth(birth);
            cus.setTel(tel);
            cus.setEmail(email);
            cus.setAddress(address);
            cnt = dao.modifyCustom(cus);
            if(cnt > 0) {
                System.out.println("업데이트 완료");
                response.sendRedirect("/pro02/Mypage.do");
            }
        } else {
            System.out.println("비밀번호 불일치");
            RequestDispatcher view = request.getRequestDispatcher("/pro02");
            view.forward(request, response);
        }
        
    }
}
