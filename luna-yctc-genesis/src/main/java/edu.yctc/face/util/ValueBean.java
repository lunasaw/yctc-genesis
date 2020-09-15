package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class ValueBean {

    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "{" + "\"value\":\'" + value + "\'" + '}';
    }
}
