package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class EyegazeItem {

    private float vectorZComponent;

    private float vectorXComponent;

    private float vectorYComponent;

    private float positionYCoordinate;

    public float getVectorZComponent() {
        return vectorZComponent;
    }

    public void setVectorZComponent(float vectorZComponent) {
        this.vectorZComponent = vectorZComponent;
    }

    public float getVectorXComponent() {
        return vectorXComponent;
    }

    public void setVectorXComponent(float vectorXComponent) {
        this.vectorXComponent = vectorXComponent;
    }

    public float getVectorYComponent() {
        return vectorYComponent;
    }

    public void setVectorYComponent(float vectorYComponent) {
        this.vectorYComponent = vectorYComponent;
    }

    public float getPositionYCoordinate() {
        return positionYCoordinate;
    }

    public void setPositionYCoordinate(float positionYCoordinate) {
        this.positionYCoordinate = positionYCoordinate;
    }

    @Override
    public String toString() {
        return "{" + "\"vectorZComponent\":" + vectorZComponent + ", \"vectorXComponent\":" + vectorXComponent
            + ", \"vectorYComponent\":" + vectorYComponent + ", \"positionYCoordinate\":" + positionYCoordinate
            + '}';
    }
}
