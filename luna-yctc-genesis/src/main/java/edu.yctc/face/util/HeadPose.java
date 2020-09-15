package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class HeadPose {

    private float yawAngle;

    private float pitchAngle;

    private float rollAngle;

    public float getYawAngle() {
        return yawAngle;
    }

    public void setYawAngle(float yawAngle) {
        this.yawAngle = yawAngle;
    }

    public float getPitchAngle() {
        return pitchAngle;
    }

    public void setPitchAngle(float pitchAngle) {
        this.pitchAngle = pitchAngle;
    }

    public float getRollAngle() {
        return rollAngle;
    }

    public void setRollAngle(float rollAngle) {
        this.rollAngle = rollAngle;
    }

    @Override
    public String toString() {
        return "{" + "\"yawAngle\":" + yawAngle + ", \"pitchAngle\":" + pitchAngle + ", \"rollAngle\":" + rollAngle
            + '}';
    }
}
