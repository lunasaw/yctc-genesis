package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class MouthStatus {

    private float close;

    private float surgicalMaskOrRespirator;

    private float open;

    private float otherOcclusion;

    public float getClose() {
        return close;
    }

    public void setClose(float close) {
        this.close = close;
    }

    public float getSurgicalMaskOrRespirator() {
        return surgicalMaskOrRespirator;
    }

    public void setSurgicalMaskOrRespirator(float surgicalMaskOrRespirator) {
        this.surgicalMaskOrRespirator = surgicalMaskOrRespirator;
    }

    public float getOpen() {
        return open;
    }

    public void setOpen(float open) {
        this.open = open;
    }

    public float getOtherOcclusion() {
        return otherOcclusion;
    }

    public void setOtherOcclusion(float otherOcclusion) {
        this.otherOcclusion = otherOcclusion;
    }

    @Override
    public String toString() {
        return "{" + "\"close\":" + close + ", \"surgicalMaskOrRespirator\":" + surgicalMaskOrRespirator + ", \"open\":"
            + open + ", \"otherOcclusion\":" + otherOcclusion + '}';
    }
}
