package shop.controller.product;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import shop.dto.Product;
import shop.dto.Receive;
import shop.model.ProductDAO;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet("/UpdateReceivePro.do")
public class UpdateReceiveProCtrl extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

        try {
            int pno = Integer.parseInt(request.getParameter("pno"));
            int amount = Integer.parseInt(request.getParameter("amount"));

            String msg = "";
            ServletContext application = request.getServletContext();
            ProductDAO dao = new ProductDAO();
            Receive rec = new Receive();
            rec.setPno(pno);
            rec.setAmount(amount);

            int cnt = dao.updateReceive(rec);
            if(cnt > 0) {
                System.out.println("db 업로드 완료");
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('상품등록이 완료되었습니다.');</script>");
                out.println("<script> location.href= '/pro02/ProList.do'; </script>");
                out.flush();

            }else{
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('상품등록에 실패하였습니다'); location.href='/pro02/UpdateReceive.do';</script>");
                out.flush();
                //response.sendRedirect(home+"/AddProduct.do");

            }
        } catch(Exception e){
            System.out.println(e.getMessage());
        }

    }
}
