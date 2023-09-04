package shop.dto;

//카테고리
public class Category {
    // 초등: 초등, 중등:중등, 고등:고등
    private String cno;
    private String cname;   //카테고리명

    public String getCno() {
        return cno;
    }

    public void setCno(String cno) {
        this.cno = cno;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Category{" +
                "cno=" + cno +
                ", cname='" + cname + '\'' +
                '}';
    }
}
