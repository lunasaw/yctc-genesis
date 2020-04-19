package edu.yctc.face.util;

import java.util.List;

/**
 * @author xiaotao
 */
public class DetectResponse extends BaseResponse {

    /** 人脸列表 */
    private List<Face> faces;

    public List<Face> getFaces() {
        return faces;
    }

    public void setFaces(List<Face> faces) {
        this.faces = faces;
    }

    @Override
    public String toString() {
        return "{" + "\"faces\":" + faces + '}';
    }
}
