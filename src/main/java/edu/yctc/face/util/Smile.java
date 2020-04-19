package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Smile extends ValueBean {

    private int threshold;

    public int getThreshold() {
        return threshold;
    }

    public void setThreshold(int threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "{" + "\"threshold\":" + threshold + '}';
    }
}
