package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Eyegaze {

    private EyegazeItem rightEyeGaze;

    private EyegazeItem leftEyeGaze;

    public EyegazeItem getRightEyeGaze() {
        return rightEyeGaze;
    }

    public void setRightEyeGaze(EyegazeItem rightEyeGaze) {
        this.rightEyeGaze = rightEyeGaze;
    }

    public EyegazeItem getLeftEyeGaze() {
        return leftEyeGaze;
    }

    public void setLeftEyeGaze(EyegazeItem leftEyeGaze) {
        this.leftEyeGaze = leftEyeGaze;
    }

    @Override
    public String toString() {
        return "{" + "\"rightEyeGaze\":" + rightEyeGaze + ", \"leftEyeGaze\":" + leftEyeGaze + '}';
    }
}
