package shop.dto;

public class Like {
    private String userid;
    private int productid;
    private String liketime;
    private String pname;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getLiketime() {
        return liketime;
    }

    public void setLiketime(String liketime) {
        this.liketime = liketime;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Like{" +
                "userid='" + userid + '\'' +
                ", productid='" + productid + '\'' +
                ", liketime='" + liketime + '\'' +
                ", pname='" + pname + '\'' +
                '}';
    }
}
