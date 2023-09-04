package shop.controller.product;


import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import shop.dto.Product;
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

@WebServlet("/UpdateProductPro.do")
public class UpdateProductProCtrl extends HttpServlet {
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String msg = "";
        ServletContext application = request.getServletContext();
        String home = application.getContextPath();
        try {
            String saveDirectory = application.getRealPath("/storage"); //실제 저장 경로
            int maxSize = 1024*1024*10;     //10MB
            String encoding = "UTF-8";

            MultipartRequest mr = new MultipartRequest(request, saveDirectory, maxSize, encoding, new DefaultFileRenamePolicy());
            Product pro = new Product();
            pro.setNo(Integer.parseInt(mr.getParameter("no")));
            pro.setPname(mr.getParameter("pname"));
            pro.setPcomment(mr.getParameter("pcomment"));
            pro.setPlist(mr.getParameter("plist"));
            pro.setPrice(Integer.parseInt(mr.getParameter("price")));
            pro.setCate(mr.getParameter("cate"));

            File upfile = null;
            Enumeration files = mr.getFileNames();

            int idx = 1;
            String item;
            String oriFile = ""; //실제 첨부된 파일경로와 이름
            String fileName = ""; //파일이름만 추출
            System.out.println("파일추출");
            while(files.hasMoreElements()) {
                item = (String) files.nextElement();
                oriFile = mr.getOriginalFileName(item); //실제 첨부된 파일경로와 이름
                fileName = mr.getFilesystemName(item);  //파일이름만 추출
                if(fileName!=null) {
                    upfile = mr.getFile(item); //실제 업로드
                    if (upfile.exists()) {
                        long filesize = upfile.length();
                        if(idx==1) {
                            pro.setImgSrc1(upfile.getName());
                            System.out.println("img1 in");
                        } else if(idx==2){
                            pro.setImgSrc2(upfile.getName());
                            System.out.println("img2 in");
                        } else if(idx==3){
                            pro.setImgSrc3(upfile.getName());
                            System.out.println("img3 in");
                        }
                        msg = "파일 업로드 성공";
                    } else {
                        msg = "파일 업로드 실패";
                    }
                }
                idx++;
            }
            System.out.println(fileName);

            ProductDAO dao = new ProductDAO();
            int cnt = dao.updateProduct(pro);

            if(cnt>0){
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('상품 수정이 완료되었습니다.');</script>");
                out.println("<script> location.href= '/pro02/ProList.do'; </script>");
                out.flush();

                //response.sendRedirect(home+"/ProList.do");

            }else{
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                out.println("<script>alert('상품등록에 실패하였습니다'); location.href='/pro02/UpdateProduct.do';</script>");
                out.flush();
                //response.sendRedirect(home+"/AddProduct.do");
            }


        } catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
