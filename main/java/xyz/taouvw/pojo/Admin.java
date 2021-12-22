package xyz.taouvw.pojo;

public class Admin {
    private String code;
    private String passwd;
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "code='" + code + '\'' +
                ", passwd='" + passwd + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
