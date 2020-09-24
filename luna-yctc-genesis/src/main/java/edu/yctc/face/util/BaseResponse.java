package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class BaseResponse {

    /** 用时 */
    private int timeUsed;

    /** 传入的图片在系统中的标识。 */
    private String imageId;

    /** 请求唯一标识 */
    private String requestId;

    /** 错误信息 */
    private String errorMessage;

    public int getTimeUsed() {
        return timeUsed;
    }

    public void setTimeUsed(int timeUsed) {
        this.timeUsed = timeUsed;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "{" + "\"timeUsed\":" + timeUsed + ", \"imageId\":\'" + imageId + "\'" + ", \"requestId\":\'" + requestId
            + "\'" + ", \"errorMessage\":\'" + errorMessage + "\'" + '}';
    }
}
