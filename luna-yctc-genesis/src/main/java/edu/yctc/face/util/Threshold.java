package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Threshold extends ValueBean {

    private float threshold;

    public float getThreshold() {
        return threshold;
    }

    public void setThreshold(float threshold) {
        this.threshold = threshold;
    }

    @Override
    public String toString() {
        return "{" + "\"threshold\":" + threshold + '}';
    }
}
