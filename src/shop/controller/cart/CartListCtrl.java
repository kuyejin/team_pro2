package shop.controller.cart;

import shop.dto.CartVO;
import shop.model.CartDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/CartList.do")
public class CartListCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("msg", "장바구니 목록을 출력합니다.");

        HttpSession session = request.getSession();
        String cid = (String) session.getAttribute("sid");
        CartDAO cartDAO = new CartDAO();
        int cntCart = cartDAO.cntCart(cid);
        session.setAttribute("cntCart",cntCart);

        CartDAO dao = new CartDAO();
        List<CartVO> cartList = dao.getByIdCartList(cid);

        request.setAttribute("cartList", cartList);


        RequestDispatcher view = request.getRequestDispatcher("/cart/cartList.jsp");
        view.forward(request, response);
    }
}
