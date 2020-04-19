package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class Face {

    private String faceToken;

    private CommonRect faceRectangle;

    private FaceLandmark landmark;

    private FaceAttributes attributes;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public CommonRect getFaceRectangle() {
        return faceRectangle;
    }

    public void setFaceRectangle(CommonRect faceRectangle) {
        this.faceRectangle = faceRectangle;
    }

    public FaceLandmark getLandmark() {
        return landmark;
    }

    public void setLandmark(FaceLandmark landmark) {
        this.landmark = landmark;
    }

    public FaceAttributes getAttributes() {
        return attributes;
    }

    public void setAttributes(FaceAttributes attributes) {
        this.attributes = attributes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Face face = (Face)o;

        if (faceToken != null ? !faceToken.equals(face.faceToken) : face.faceToken != null) {
            return false;
        }
        if (faceRectangle != null ? !faceRectangle.equals(face.faceRectangle) : face.faceRectangle != null) {
            return false;
        }
        if (landmark != null ? !landmark.equals(face.landmark) : face.landmark != null) {
            return false;
        }
        return attributes != null ? attributes.equals(face.attributes) : face.attributes == null;
    }

    @Override
    public int hashCode() {
        int result = faceToken != null ? faceToken.hashCode() : 0;
        result = 31 * result + (faceRectangle != null ? faceRectangle.hashCode() : 0);
        result = 31 * result + (landmark != null ? landmark.hashCode() : 0);
        result = 31 * result + (attributes != null ? attributes.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "{" + "\"faceToken\":\'" + faceToken + "\'" + ", \"faceRectangle\":" + faceRectangle + ", \"landmark\":"
            + landmark + ", \"attributes\":" + attributes + '}';
    }
}
