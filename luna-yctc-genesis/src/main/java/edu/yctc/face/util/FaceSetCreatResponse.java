package edu.yctc.face.util;

import java.util.Arrays;

/**
 * @author xiaotao
 */
public class FaceSetCreatResponse extends BaseResponse {

    private String facesetToken;

    private String outerId;

    private int faceAdded;

    private int faceCount;

    private FailureDetail[] failureDetail;

    public String getFacesetToken() {
        return facesetToken;
    }

    public void setFacesetToken(String facesetToken) {
        this.facesetToken = facesetToken;
    }

    public String getOuterId() {
        return outerId;
    }

    public void setOuterId(String outerId) {
        this.outerId = outerId;
    }

    public int getFaceAdded() {
        return faceAdded;
    }

    public void setFaceAdded(int faceAdded) {
        this.faceAdded = faceAdded;
    }

    public int getFaceCount() {
        return faceCount;
    }

    public void setFaceCount(int faceCount) {
        this.faceCount = faceCount;
    }

    public FailureDetail[] getFailureDetail() {
        return failureDetail;
    }

    public void setFailureDetail(FailureDetail[] failureDetail) {
        this.failureDetail = failureDetail;
    }

    @Override
    public String toString() {
        return "{" + "\"facesetToken\":\'" + facesetToken + "\'" + ", \"outerId\":\'" + outerId + "\'"
            + ", \"faceAdded\":" + faceAdded + ", \"faceCount\":" + faceCount + ", \"failureDetail\":"
            + Arrays.toString(failureDetail) + '}';
    }

    private static class FailureDetail {

        private String faceToken;
        private String reason;

        @Override
        public String toString() {
            return "{" + "\"faceToken\":\'" + faceToken + "\'" + ", \"reason\":\'" + reason + "\'" + '}';
        }
    }
}
