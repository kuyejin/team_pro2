package shop.dto;

public class Review {
    private int no;
    private String cid;
    private String content;
    private int par;
    private String pname;

    private String resdate;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPar() {
        return par;
    }

    public void setPar(int par) {
        this.par = par;
    }

    public String getResdate() {
        return resdate;
    }

    public void setResdate(String resdate) {
        this.resdate = resdate;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return "Review{" +
                "no=" + no +
                ", cid='" + cid + '\'' +
                ", content='" + content + '\'' +
                ", par=" + par +
                ", pname='" + pname + '\'' +
                ", resdate='" + resdate + '\'' +
                '}';
    }
}
