package shop.dto;

// 상품
public class Product {
    private int no; // 상품번호
    private String cate; // 카테고리
    private String cateno; // 카테고리번호
    private String pname; // 상품명
    private String pcomment; // 상품설명
    private String plist; // 상품목차
    private int pqty; // 상품 수량
    private int price; // 상품단가
    private String imgSrc1; // 상품이미지
    private String imgSrc2; // 상품이미지
    private String imgSrc3; // 상품이미지
    private String resdate; // 등록일

    private String cname;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCate() {
        return cate;
    }

    public void setCate(String cate) {
        this.cate = cate;
    }

    public String getCateno() {
        return cateno;
    }

    public void setCateno(String cateno) {
        this.cateno = cateno;
    }

    public int getPqty() {
        return pqty;
    }

    public void setPqty(int pqty) {
        this.pqty = pqty;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPcomment() {
        return pcomment;
    }

    public void setPcomment(String pcomment) {
        this.pcomment = pcomment;
    }

    public String getPlist() {
        return plist;
    }

    public void setPlist(String plist) {
        this.plist = plist;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getImgSrc1() {
        return imgSrc1;
    }

    public void setImgSrc1(String imgSrc1) {
        this.imgSrc1 = imgSrc1;
    }

    public String getImgSrc2() {
        return imgSrc2;
    }

    public void setImgSrc2(String imgSrc2) {
        this.imgSrc2 = imgSrc2;
    }

    public String getImgSrc3() {
        return imgSrc3;
    }

    public void setImgSrc3(String imgSrc3) {
        this.imgSrc3 = imgSrc3;
    }

    public String getResdate() {
        return resdate;
    }

    public void setResdate(String resdate) {
        this.resdate = resdate;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Product{" +
                "no=" + no +
                ", cate='" + cate + '\'' +
                ", cateno='" + cateno + '\'' +
                ", pname='" + pname + '\'' +
                ", pcomment='" + pcomment + '\'' +
                ", plist='" + plist + '\'' +
                ", pqty=" + pqty +
                ", price=" + price +
                ", imgSrc1='" + imgSrc1 + '\'' +
                ", imgSrc2='" + imgSrc2 + '\'' +
                ", imgSrc3='" + imgSrc3 + '\'' +
                ", resdate='" + resdate + '\'' +
                ", cname='" + cname + '\'' +
                '}';
    }
}
