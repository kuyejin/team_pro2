package shop.dto;

public class Fileud {
    private String uname;
    private String subject;
    private String content;
    private String filename;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "Filetest{" +
                "uname='" + uname + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", filename='" + filename + '\'' +
                '}';
    }
}
