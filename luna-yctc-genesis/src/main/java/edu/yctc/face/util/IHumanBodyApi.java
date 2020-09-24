package edu.yctc.face.util;

import java.util.Map;

/**
 * @author by licheng on 2018/7/3.
 */

public interface IHumanBodyApi {

    String BASE_URL = "https://api-cn.faceplusplus.com/humanbodypp";

    String API_HUMANBODY_DETECT = BASE_URL + "/v1/detect";
    String API_HUMANBODY_SEGMENT = BASE_URL + "/v2/segment";
    String API_HUMANBODY_GUSTURE = BASE_URL + "/beta/gesture";
    String API_HUMANBODY_SKELETON = BASE_URL + "/v1/skeleton";

    /**
     * 人形抠像
     * 
     * @param params
     * @param filePath
     * @param callBack
     */
    void segment(Map<String, String> params, byte[] filePath, IFaceCallBack<HumanSegmentResponse> callBack);

    /**
     * 人体骨骼
     * 
     * @param params
     * @param filePath
     * @param callBack
     */
    void skeleton(Map<String, String> params, byte[] filePath, IFaceCallBack<SkeletonResponse> callBack);
}
