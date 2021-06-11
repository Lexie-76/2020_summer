package test.domain;

public class Data2 {
    private String name;
    private Integer value;

    public Data2() {
    }

    public Data2(String name, Integer value) {
        this.value = value;
        this.name = name;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public Integer getvalue() {
        return value;
    }

    public void setvalue(Integer value) {
        this.value = value;
    }
}
