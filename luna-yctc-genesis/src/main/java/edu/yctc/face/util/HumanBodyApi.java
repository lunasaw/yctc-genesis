package edu.yctc.face.util;

import java.util.Map;

/**
 * @author xiaotao
 */
public class HumanBodyApi implements IHumanBodyApi {

    @Override
    public void segment(Map<String, String> params, byte[] filePath, IFaceCallBack<HumanSegmentResponse> callBack) {
        HttpUtils.post(API_HUMANBODY_SEGMENT, params, filePath,
            new TransCallBack<>(callBack, HumanSegmentResponse.class));
    }

    @Override
    public void skeleton(Map<String, String> params, byte[] filePath, IFaceCallBack<SkeletonResponse> callBack) {
        HttpUtils.post(API_HUMANBODY_SKELETON, params, filePath, new TransCallBack<>(callBack, SkeletonResponse.class));
    }
}
