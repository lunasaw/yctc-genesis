package edu.yctc.face.util;

/**
 * @author xiaotao
 */
public class HumanSegmentResponse extends BaseResponse {

    /** 灰度图 */
    private String result;

    /** 抠出的人像图片 */
    private String bodyImage;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getBodyImage() {
        return bodyImage;
    }

    public void setBodyImage(String bodyImage) {
        this.bodyImage = bodyImage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        HumanSegmentResponse that = (HumanSegmentResponse)o;

        if (result != null ? !result.equals(that.result) : that.result != null) {
            return false;
        }
        return bodyImage != null ? bodyImage.equals(that.bodyImage) : that.bodyImage == null;
    }

    @Override
    public int hashCode() {
        int result1 = result != null ? result.hashCode() : 0;
        result1 = 31 * result1 + (bodyImage != null ? bodyImage.hashCode() : 0);
        return result1;
    }

    @Override
    public String toString() {
        return "{" + "\"result\":\'" + result + "\'" + ", \"bodyImage\":\'" + bodyImage + "\'" + '}';
    }
}
