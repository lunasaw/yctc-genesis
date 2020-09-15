package edu.yctc.face.util;

import java.util.Map;

/**
 * @author xiaotao
 */
public interface IFaceApi {

    String BASE_URL = "https://api-cn.faceplusplus.com/facepp";

    String API_DETECT = BASE_URL + "/v3/detect";
    String API_COMPARE = BASE_URL + "/v3/compare";
    String API_SEARCH = BASE_URL + "/v3/search";
    String API_FACE_SET_CREATE = BASE_URL + "/v3/faceset/create";
    String API_FACE_SET_ADD_FACE = BASE_URL + "/v3/faceset/addface";
    String API_FACE_SET_REMOVE_FACE = BASE_URL + "/v3/faceset/removeface";
    String API_FACE_SET_UPDATE = BASE_URL + "/v3/faceset/update";
    String API_FACE_SET_GET_FACESETS = BASE_URL + "/v3/faceset/getfacesets";
    String API_FACE_SET_GET_DETAIL = BASE_URL + "/v3/faceset/getdetail";
    String API_FACE_SET_DELETE = BASE_URL + "/v3/faceset/delete";

    String API_FACE_SET_ADD_FACE_ASYNC = BASE_URL + "/v3/faceset/async/addface";
    String API_FACE_SET_REMOVE_FACE_ASYNC = BASE_URL + "/v3/faceset/async/removeface";
    String API_FACE_SET_TASK_QUERY = BASE_URL + "/v3/faceset/async/task_status";

    String API_FACE_ANALYZE = BASE_URL + "/v3/face/analyze";
    String API_FACE_GET_DETAIL = BASE_URL + "/v3/face/getdetail";
    String API_FACE_SET_USER_ID = BASE_URL + "/v3/face/setuserid";

    String API_BEAUTY = BASE_URL + "/beta/beautify";

    /**
     * 人脸检测
     * 
     * @param params
     * @param filePath
     * @param callBack
     */
    void detect(Map<String, String> params, byte[] filePath, IFaceCallBack<DetectResponse> callBack);

    /**
     * 从 faceSet 中检索人脸
     * 
     * @param params
     * @param callBack
     */
    void search(Map<String, String> params, IFaceCallBack<SearchResponse> callBack);

    /**
     * 从 faceSet 中检索人脸
     * 
     * @param params
     * @param filePath
     * @param callBack
     */
    void search(Map<String, String> params, byte[] filePath, IFaceCallBack<SearchResponse> callBack);

    /**
     * 创建 faceSet
     * 
     * @param params
     * @param callBack
     */
    void facesetCreate(Map<String, String> params, IFaceCallBack<FaceSetCreatResponse> callBack);

    /**
     * 向 faceset 添加人脸
     * 
     * @param params
     * @param callBack
     */
    void facesetAddFace(Map<String, String> params, IFaceCallBack<FaceSetAddResponse> callBack);

    /**
     * 从 faceSet 中删除人脸
     * 
     * @param params
     * @param callBack
     */
    void facesetRemoveFace(Map<String, String> params, IFaceCallBack<FaceSetRemoveResponse> callBack);

    /**
     * 删除 faceSet
     * 
     * @param params
     * @param callBack
     */
    void facesetDelete(Map<String, String> params, IFaceCallBack<FaceSetDeleteResponse> callBack);
}
