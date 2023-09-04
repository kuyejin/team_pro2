package shop.dto;

public class PaymentVO {
    private int sno;	        //(auto)결제번호
    private String cid;         //고객아이디
    private int pno;            //상품번호
    private int amount;         //구매수량
    private String pmethod;	    //+결제수단
    private String pcom;		//+결제 대행사
    private String cnum;		//+결제카드(계좌)번호
    private int payprice;		//+결제금액
    private String dno;            //배송코드
    private String pname;       //상품명
    private int pstate;         //배송 상태

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
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

    public String getDno() {
        return dno;
    }

    public void setDno(String dno) {
        this.dno = dno;
    }

    public String getPmethod() {
        return pmethod;
    }

    public void setPmethod(String pmethod) {
        this.pmethod = pmethod;
    }

    public String getPcom() {
        return pcom;
    }

    public void setPcom(String pcom) {
        this.pcom = pcom;
    }

    public String getCnum() {
        return cnum;
    }

    public void setCnum(String cnum) {
        this.cnum = cnum;
    }

    public int getPayprice() {
        return payprice;
    }

    public void setPayprice(int payprice) {
        this.payprice = payprice;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    @Override
    public String toString() {
        return "PaymentVO{" +
                "sno=" + sno +
                ", cid='" + cid + '\'' +
                ", pno=" + pno +
                ", amount=" + amount +
                ", pmethod='" + pmethod + '\'' +
                ", pcom='" + pcom + '\'' +
                ", cnum='" + cnum + '\'' +
                ", payprice=" + payprice +
                ", dno='" + dno + '\'' +
                ", pname='" + pname + '\'' +
                ", pstate=" + pstate +
                '}';
    }
}
