package shop.controller.inventory;

import shop.dto.Product;
import shop.model.InventoryDAO;
import shop.model.ProductDAO;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

@WebServlet("/InventoryList.do")
public class InventoryListCtrl extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("msg", "상품 재고를 출력합니다.");

        ProductDAO prodao = new ProductDAO(); //정보 가져오기
        List<Product> proList = prodao.getProductList();

        InventoryDAO invdao = new InventoryDAO();
        List invList = invdao.getInvetoryList();


        request.setAttribute("proList", proList);
        request.setAttribute("invList", invList);


        RequestDispatcher view = request.getRequestDispatcher("/WEB-INF/admin/inventoryList.jsp");
        view.forward(request, response);




    }
}
