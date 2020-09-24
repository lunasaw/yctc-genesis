package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class FaceToken extends BaseResponse {

    private String faceToken;

    private String userId;

    public String getFaceToken() {
        return faceToken;
    }

    public void setFaceToken(String faceToken) {
        this.faceToken = faceToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "{" + "\"faceToken\":\'" + faceToken + "\'" + ", \"userId\":\'" + userId + "\'" + '}';
    }
}
