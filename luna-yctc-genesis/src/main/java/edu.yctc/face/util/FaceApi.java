package edu.yctc.face.util;

import java.util.Map;

/**
 * @author xiaotao
 */
public class FaceApi implements IFaceApi {

    @Override
    public void detect(Map<String, String> params, byte[] filePath, IFaceCallBack<DetectResponse> callBack) {
        HttpUtils.post(API_DETECT, params, filePath, new TransCallBack<>(callBack, DetectResponse.class));
    }

    @Override
    public void search(Map<String, String> params, IFaceCallBack<SearchResponse> callBack) {
        search(params, null, callBack);
    }

    @Override
    public void search(Map<String, String> params, byte[] filePath, IFaceCallBack<SearchResponse> callBack) {
        HttpUtils.post(API_SEARCH, params, filePath, new TransCallBack<>(callBack, SearchResponse.class));
    }

    @Override
    public void facesetCreate(Map<String, String> params, IFaceCallBack<FaceSetCreatResponse> callBack) {
        HttpUtils.post(API_FACE_SET_CREATE, params, new TransCallBack<>(callBack, FaceSetCreatResponse.class));
    }

    @Override
    public void facesetAddFace(Map<String, String> params, IFaceCallBack<FaceSetAddResponse> callBack) {
        HttpUtils.post(API_FACE_SET_ADD_FACE, params, new TransCallBack<>(callBack, FaceSetAddResponse.class));
    }

    @Override
    public void facesetRemoveFace(Map<String, String> params, IFaceCallBack<FaceSetRemoveResponse> callBack) {
        HttpUtils.post(API_FACE_SET_REMOVE_FACE, params, new TransCallBack<>(callBack, FaceSetRemoveResponse.class));
    }

    @Override
    public void facesetDelete(Map<String, String> params, IFaceCallBack<FaceSetDeleteResponse> callBack) {
        HttpUtils.post(API_FACE_SET_DELETE, params, new TransCallBack<>(callBack, FaceSetDeleteResponse.class));
    }

}
