package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class SearchFaceResult {

    private String faceToken;

    private float confidence;

    private String userId;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public float getConfidence() {
        return confidence;
    }

    public void setConfidence(float confidence) {
        this.confidence = confidence;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" + "\"faceToken\":\'" + faceToken + "\'" + ", \"confidence\":" + confidence + ", \"userId\":\'"
            + userId + "\'" + '}';
    }
}
