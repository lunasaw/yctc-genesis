package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class EyeStatusAttribute {

    private float normalQlassEyeOpen;

    private float noGlassEyeClose;

    private float occlusion;

    private float noGlassEyeOpen;

    private float normalGlassEyeClose;

    private float darkGlasses;

    public float getNormalGlassEyeOpen() {
        return normalQlassEyeOpen;
    }

    public void setNormalGlassEyeOpen(float normalQlassEyeOpen) {
        this.normalQlassEyeOpen = normalQlassEyeOpen;
    }

    public float getNoGlassEyeClose() {
        return noGlassEyeClose;
    }

    public void setNoGlassEyeClose(float noGlassEyeClose) {
        this.noGlassEyeClose = noGlassEyeClose;
    }

    public float getOcclusion() {
        return occlusion;
    }

    public void setOcclusion(float occlusion) {
        this.occlusion = occlusion;
    }

    public float getNoGlassEyeOpen() {
        return noGlassEyeOpen;
    }

    public void setNoGlassEyeOpen(float noGlassEyeOpen) {
        this.noGlassEyeOpen = noGlassEyeOpen;
    }

    public float getNormalGlassEyeClose() {
        return normalGlassEyeClose;
    }

    public void setNormalGlassEyeClose(float normalGlassEyeClose) {
        this.normalGlassEyeClose = normalGlassEyeClose;
    }

    public float getDarkGlasses() {
        return darkGlasses;
    }

    public void setDarkGlasses(float darkGlasses) {
        this.darkGlasses = darkGlasses;
    }

    @Override
    public String toString() {
        return "{" + "\"normalQlassEyeOpen\":" + normalQlassEyeOpen + ", \"noGlassEyeClose\":" + noGlassEyeClose
            + ", \"occlusion\":" + occlusion + ", \"noGlassEyeOpen\":" + noGlassEyeOpen
            + ", \"normalGlassEyeClose\":" + normalGlassEyeClose + ", \"darkGlasses\":" + darkGlasses + '}';
    }
}
