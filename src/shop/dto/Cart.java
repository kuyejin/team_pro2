package shop.dto;

public class Cart {
    private int cartno;     //카트번호
    private String cid;     //고객아이디
    private int pno;        //제품번호
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

    @Override
    public String toString() {
        return "Cart{" +
                "cartno=" + cartno +
                ", cid='" + cid + '\'' +
                ", pno=" + pno +
                ", amount=" + amount +
                '}';
    }
}
