package shop.controller.product;

import shop.model.ProductDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/DeleteProduct.do")
public class DeleteProductCtrl extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int no = Integer.parseInt(request.getParameter("no"));


        String msg = "상품 정보를 삭제합니다.";
        ServletContext application = request.getServletContext();
        String home = application.getContextPath();

        ProductDAO dao = new ProductDAO();
        int cnt = dao.delProduct(no);


        if(cnt>0){
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('상품 삭제 완료되었습니다.');</script>");
            out.println("<script> location.href= '/pro02/ProList.do'; </script>");
            out.flush();

            //response.sendRedirect(home+"/ProList.do");

        }else{
            response.setContentType("text/html; charset=UTF-8");
            PrintWriter out = response.getWriter();
            out.println("<script>alert('상품등록에 실패하였습니다'); location.href='/product/getProduct.jsp';</script>");
            out.flush();
            //response.sendRedirect(home+"/AddProduct.do");
        }
    }
}
