package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class EyeStatus {

    private EyeStatusAttribute leftEyeStatus;

    private EyeStatusAttribute rightEyeStatus;

    public EyeStatusAttribute getLeftEyeStatus() {
        return leftEyeStatus;
    }

    public void setLeftEyeStatus(EyeStatusAttribute leftEyeStatus) {
        this.leftEyeStatus = leftEyeStatus;
    }

    public EyeStatusAttribute getRightEyeStatus() {
        return rightEyeStatus;
    }

    public void setRightEyeStatus(EyeStatusAttribute rightEyeStatus) {
        this.rightEyeStatus = rightEyeStatus;
    }

    @Override
    public String toString() {
        return "{" + "\"leftEyeStatus\":" + leftEyeStatus + ", \"rightEyeStatus\":" + rightEyeStatus + '}';
    }
}
