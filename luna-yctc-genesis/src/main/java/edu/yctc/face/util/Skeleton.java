package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Skeleton {

    private BodyRectangle bodyRectangle;

    private Landmark landmark;

    public BodyRectangle getBodyRectangle() {
        return bodyRectangle;
    }

    public void setBodyRectangle(BodyRectangle bodyRectangle) {
        this.bodyRectangle = bodyRectangle;
    }

    public Landmark getLandmark() {
        return landmark;
    }

    public void setLandmark(Landmark landmark) {
        this.landmark = landmark;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Skeleton skeleton = (Skeleton)o;

        if (bodyRectangle != null ? !bodyRectangle.equals(skeleton.bodyRectangle) : skeleton.bodyRectangle != null) {
            return false;
        }
        return landmark != null ? landmark.equals(skeleton.landmark) : skeleton.landmark == null;
    }

    @Override
    public int hashCode() {
        int result = bodyRectangle != null ? bodyRectangle.hashCode() : 0;
        result = 31 * result + (landmark != null ? landmark.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + "\"bodyRectangle\":" + bodyRectangle + ", \"landmark\":" + landmark + '}';
    }
}
