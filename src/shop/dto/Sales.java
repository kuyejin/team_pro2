package shop.dto;

public class Sales {
    private int pno;
    private String pname;
    private int cnt;
    private int money;

    public int getPno() {
        return pno;
    }

    public void setPno(int pno) {
        this.pno = pno;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public int getCnt() {
        return cnt;
    }

    public void setCnt(int cnt) {
        this.cnt = cnt;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    @Override
    public String toString() {
        return "Sales{" +
                "pno=" + pno +
                ", pname='" + pname + '\'' +
                ", cnt=" + cnt +
                ", money=" + money +
                '}';
    }
}
