package test.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Data1 implements Serializable {
    private List<String> names = new ArrayList<>();
    private List<Integer> value = new ArrayList<>();

    public Data1() {
    }

    public Data1(List<String> names, List<Integer> value) {
        this.names = names;
        this.value = value;
    }

    public List<String> getnames() {
        return names;
    }

    public void setnames(List<String> names) {
        this.names = names;
    }

    public List<Integer> getvalue() {
        return value;
    }

    public void setvalue(List<Integer> value) {
        this.value = value;
    }
}

