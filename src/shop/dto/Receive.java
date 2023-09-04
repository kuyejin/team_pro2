package shop.dto;

public class Receive {
    private int rno;         //입고번호(auto)
    private int pno;        //입고 제품번호
    private int amount;     //입고수량
    private int rprice;     //입고가격
    private String resdate; //입고일

    public int getRno() {
        return rno;
    }

    public void setRno(int rno) {
        this.rno = rno;
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

    public int getRprice() {
        return rprice;
    }

    public void setRprice(int rprice) {
        this.rprice = rprice;
    }

    public String getResdate() {
        return resdate;
    }

    public void setResdate(String resdate) {
        this.resdate = resdate;
    }

    @Override
    public String toString() {
        return "Receive{" +
                "rno=" + rno +
                ", pno=" + pno +
                ", amount=" + amount +
                ", rprice=" + rprice +
                ", resdate='" + resdate + '\'' +
                '}';
    }
}
