package shop.dto;

public class DeliveryInfoView {
    private int dno;	//(auto)배송코드
    private int sno;    //결제번호
    private String cid; //고객아이디
    private String daddr;	//+배송지
    private String custel; //+고객연락처
    private String pcom; //(admin)배송회사
    private String ptel; //(admin)배송기사연락처
    private int pstate;	//(0:배송전,1:배송중,2:도착,3:구매결정)-배송상태
    private String sdate; // 결제일
    private String rdate; // 배송시작일
    private String bcode; //운송 번호
    private String pname; // 제품 번호
    private int amount; // 구매 수량

    public int getDno() {
        return dno;
    }

    public void setDno(int dno) {
        this.dno = dno;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getDaddr() {
        return daddr;
    }

    public void setDaddr(String daddr) {
        this.daddr = daddr;
    }

    public String getCustel() {
        return custel;
    }

    public void setCustel(String custel) {
        this.custel = custel;
    }

    public String getPcom() {
        return pcom;
    }

    public void setPcom(String pcom) {
        this.pcom = pcom;
    }

    public String getPtel() {
        return ptel;
    }

    public void setPtel(String ptel) {
        this.ptel = ptel;
    }

    public int getPstate() {
        return pstate;
    }

    public void setPstate(int pstate) {
        this.pstate = pstate;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public String getRdate() {
        return rdate;
    }

    public void setRdate(String rdate) {
        this.rdate = rdate;
    }

    public String getBcode() {
        return bcode;
    }

    public void setBcode(String bcode) {
        this.bcode = bcode;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "DeliveryInfoView{" +
                "dno=" + dno +
                ", sno=" + sno +
                ", cid='" + cid + '\'' +
                ", daddr='" + daddr + '\'' +
                ", custel='" + custel + '\'' +
                ", pcom='" + pcom + '\'' +
                ", ptel='" + ptel + '\'' +
                ", pstate=" + pstate +
                ", sdate='" + sdate + '\'' +
                ", rdate='" + rdate + '\'' +
                ", bcode='" + bcode + '\'' +
                ", pname='" + pname + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
