package shop.dto;

public class CartVO {
    private int cartno;     //카트번호
    private String cid;     //고객아이디
    private String name;     //고객명
    private int pno;        //제품번호
    private String pname;   //제품번호
    private String img1; // 제품 이미지
    private int amount;     //제품수량

    public int getCartno() {
        return cartno;
    }

    public void setCartno(int cartno) {
        this.cartno = cartno;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getImg1() {
        return img1;
    }

    public void setImg1(String img1) {
        this.img1 = img1;
    }

    @Override
    public String toString() {
        return "CartVO{" +
                "cartno=" + cartno +
                ", cid='" + cid + '\'' +
                ", name='" + name + '\'' +
                ", pno=" + pno +
                ", pname='" + pname + '\'' +
                ", img1='" + img1 + '\'' +
                ", amount=" + amount +
                '}';
    }
}
