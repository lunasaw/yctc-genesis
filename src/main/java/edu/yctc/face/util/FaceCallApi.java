package edu.yctc.face.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xiaotao
 */
public class FaceCallApi implements IFaceApi, IHumanBodyApi {

    private static final String API_KEY = "api_key";
    private static final String API_SECRET = "api_secret";

    private Map<String, String> autoInfo = new HashMap<>();

    /** 人脸识别Api */
    private IFaceApi faceApi = new FaceApi();
    /** 人体识别Api */
    private IHumanBodyApi humanBodyApi = new HumanBodyApi();

    public FaceCallApi(String apiKey, String apiSecret) {
        autoInfo.put(API_KEY, apiKey);
        autoInfo.put(API_SECRET, apiSecret);
    }

    @Override
    public void detect(Map<String, String> params, byte[] filePath, IFaceCallBack<DetectResponse> callBack) {
        faceApi.detect(buildParams(params), filePath, callBack);
    }

    @Override
    public void search(Map<String, String> params, IFaceCallBack<SearchResponse> callBack) {
        faceApi.search(buildParams(params), callBack);
    }

    @Override
    public void search(Map<String, String> params, byte[] filePath, IFaceCallBack<SearchResponse> callBack) {
        faceApi.search(buildParams(params), filePath, callBack);
    }

    @Override
    public void facesetCreate(Map<String, String> params, IFaceCallBack<FaceSetCreatResponse> callBack) {
        faceApi.facesetCreate(buildParams(params), callBack);
    }

    @Override
    public void facesetAddFace(Map<String, String> params, IFaceCallBack<FaceSetAddResponse> callBack) {
        faceApi.facesetAddFace(buildParams(params), callBack);
    }

    @Override
    public void facesetRemoveFace(Map<String, String> params, IFaceCallBack<FaceSetRemoveResponse> callBack) {
        faceApi.facesetRemoveFace(buildParams(params), callBack);
    }

    @Override
    public void facesetDelete(Map<String, String> params, IFaceCallBack<FaceSetDeleteResponse> callBack) {
        faceApi.facesetDelete(buildParams(params), callBack);
    }

    /***********************************************************************************************/

    @Override
    public void segment(Map<String, String> params, byte[] filePath, IFaceCallBack<HumanSegmentResponse> callBack) {
        humanBodyApi.segment(buildParams(params), filePath, callBack);
    }

    @Override
    public void skeleton(Map<String, String> params, byte[] filePath, IFaceCallBack<SkeletonResponse> callBack) {
        humanBodyApi.skeleton(buildParams(params), filePath, callBack);
    }

    /***********************************************************************************************/

    private Map<String, String> buildParams(Map<String, String> params) {
        if (null != params) {
            params.putAll(autoInfo);
        } else {
            params = new HashMap<>(autoInfo);
        }
        return params;
    }
}
