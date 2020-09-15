package edu.yctc.face.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Maps;

/**
 * Face++API调用工具类
 * 
 * @author xiaotao
 */
public class MyFaceApi {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** 常量 */
    private final static String FACESET_EXIST = "FACESET_EXIST";

    /** 人脸识别外部调用类 */
    private static FaceCallApi facePpApi = new FaceCallApi("4R-39l5gk0NjgAT1eXmUiS93bzs79GXD",
        "LXpYaIkj7xvQAuGEtmkW_3uXaqjBDLPM");

    /**
     * 创建FaceSet
     * 
     * @param display_name 人脸集合的名字
     * @param outer_id 账号下全局唯一的 FaceSet 自定义标识，可以用来管理 FaceSet 对象。
     */
    public static void myFaceSetCreate(String displayName, String outerId) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("display_name", displayName);
        params.put("outer_id", outerId);
        facePpApi.facesetCreate(params, new IFaceCallBack<FaceSetCreatResponse>() {

            @Override
            public void onSuccess(FaceSetCreatResponse faceSetCreatResponse) {
                LOG.info("FaceSet:{} create success", outerId);
            }

            @Override
            public void onFailed(String s) {
                if (FACESET_EXIST.equals(s)) {
                    /** 如果FaceSet存在就不重复创建 */
                    LOG.error("FaceSet:{} already existed", outerId);
                } else {
                    LOG.error("MyFaceSetCreate fail,msg={}", s);
                }
            }

        });
    }

    /**
     * 移除一个FaceSet中的某些或者全部face_token
     * 
     * @param outer_id 账号下全局唯一的 FaceSet 自定义标识，可以用来管理 FaceSet 对象。
     * @param face_tokens 需要移除的人脸标识,用逗号分隔(传入“RemoveAllFaceTokens”则会移除FaceSet内所有的face_token)
     */
    public static void myFacesetRemoveFace(String outerId, String faceTokens) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("outer_id", outerId);
        params.put("face_tokens", faceTokens);
        facePpApi.facesetRemoveFace(params, new IFaceCallBack<FaceSetRemoveResponse>() {

            @Override
            public void onSuccess(FaceSetRemoveResponse facesetRemoveFace) {
                LOG.info("face_token:{} from FaceSet:{} Remove success", faceTokens, outerId);
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyFacesetRemoveFace fail,msg={}", s);
            }

        });
    }

    /**
     * 删除FaceSet
     * 
     * @param outer_id 账号下全局唯一的 FaceSet 自定义标识，可以用来管理 FaceSet 对象。
     */
    public static void myFacesetDelete(String outerId) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("outer_id", outerId);
        params.put("check_empty", "0");
        facePpApi.facesetDelete(params, new IFaceCallBack<FaceSetDeleteResponse>() {

            @Override
            public void onSuccess(FaceSetDeleteResponse faceSetDeleteResponse) {
                LOG.info("FaceSet:{} delete success", outerId);
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyFacesetDelete fail,msg={}", s);
            }

        });
    }

    /**
     * 将face_tokens加入FaceSet
     * 
     * @param outer_id 用户提供的 FaceSet 标识
     * @param face_tokens 人脸标识
     */
    public static void myFaceSetAdd(String outerId, String faceTokens) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("outer_id", outerId);
        params.put("face_tokens", faceTokens);
        facePpApi.facesetAddFace(params, new IFaceCallBack<FaceSetAddResponse>() {

            @Override
            public void onSuccess(FaceSetAddResponse faceSetAddResponse) {
                LOG.info("FaceSet:{} add face_token:{} success", outerId, faceTokens);
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyFaceSetAdd fail,msg={}", s);
            }

        });
    }

    /**
     * 识别图片中的人脸
     * 
     * @param destUrl URL路径
     * @param attributes 需要用到的参数
     * @param faces 存放人脸的容器
     */
    public static void myDetectFromUrl(String destUrl, String attributes, List<Face> faces) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        if (null != attributes) {
            params.put("return_attributes", attributes);
        }
        /** 检测106个人脸关键点 */
        params.put("return_landmark", "2");
        facePpApi.detect(params, LoadImage.getBytesFromUrl(destUrl), new IFaceCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                faces.addAll(detectResponse.getFaces());
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyDetect fail,msg={}", s);
            }

        });
    }

    /**
     * 识别图片中的人脸
     * 
     * @param imgPath 图片路径
     * @param attributes 需要用到的参数
     * @param faces 存放人脸的容器
     */
    public static List<Face> myDetect(String imgPath, String attributes, List<Face> faces) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        if (null != attributes) {
            params.put("return_attributes", attributes);
        }
        /** 检测106个人脸关键点 */
        params.put("return_landmark", "2");
        facePpApi.detect(params, LoadImage.getBytesFromFile(new File(imgPath)), new IFaceCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                faces.addAll(detectResponse.getFaces());
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyDetect fail,msg={}", s);
            }

        });
        return faces;
    }

    /**
     * 识别图片中的人脸
     * 
     * @param javaImage 图片
     * @param attributes 需要用到的参数
     * @param faces 存放人脸的容器
     */
    public static void myDetect(BufferedImage javaImage, String attributes, List<Face> faces) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        if (null != attributes) {
            params.put("return_attributes", attributes);
        }
        /** 检测106个人脸关键点 */
        params.put("return_landmark", "2");
        facePpApi.detect(params, LoadImage.getBytesFromBufferedImage(javaImage), new IFaceCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                faces.addAll(detectResponse.getFaces());
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyDetect fail,msg={}", s);
            }

        });
    }

    /**
     * 将证件照导入FaceSet
     * 
     * @param filePath 图片路径
     * @param attributes 需要用到的参数
     * @param outer_id FaceSet 标识
     * @param faces 存放face_token的容器
     */
    public static void myDetect(String filePath, String attributes, String outerId, List<String> faces) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(2);
        params.put("return_attributes", attributes);
        /** 检测106个人脸关键点 */
        params.put("return_landmark", "2");
        facePpApi.detect(params, LoadImage.getBytesFromFile(new File(filePath)), new IFaceCallBack<DetectResponse>() {

            @Override
            public void onSuccess(DetectResponse detectResponse) {
                faces.add(detectResponse.getFaces().get(0).getFaceToken());
                /** 加入FaceSet 一张证件照只有一个人脸 */
                myFaceSetAdd(outerId, detectResponse.getFaces().get(0).getFaceToken());
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MyDetect fail,msg={}", s);
            }

        });
    }

    /**
     * 在FaceSet搜索最像的人脸
     * 
     * @param face_token 进行搜索的目标人脸的 face_token
     * @param outer_id 用户自定义的 FaceSet 标识
     * @param thresholds 相似度
     * @param faces 存放人脸的容器
     */
    public static void mySearch(String faceToken, String outerId, List<Float> thresholds, List<String> faces) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(3);
        params.put("face_token", faceToken);
        params.put("outer_id", outerId);
        params.put("return_result_count", "1");
        facePpApi.search(params, new IFaceCallBack<SearchResponse>() {

            @Override
            public void onSuccess(SearchResponse searchResponse) {
                /**
                 * thresholds 一组用于参考的置信度阈值，包含以下三个字段。每个字段的值为一个 [0,100] 的浮点数，小数点后 3 位有效数字。 1e-3：误识率为千分之一的置信度阈值；
                 * 1e-4：误识率为万分之一的置信度阈值； 1e-5：误识率为十万分之一的置信度阈值； 如果置信值低于“千分之一”阈值则不建议认为是同一个人；如果置信值超过“十万分之一”阈值，则是同一个人的几率非常高。
                 * 请注意：阈值不是静态的，每次返回的阈值不保证相同，所以没有持久化保存阈值的必要，更不要将当前调用返回的 confidence 与之前调用返回的阈值比较。
                 * 注：如果传入图片但图片中未检测到人脸，则无法进行人脸搜索，本字段不返回。
                 */
                thresholds.add(searchResponse.getResults().get(0).getConfidence());
                thresholds.add(searchResponse.getThresholds().get("1e-5"));
                thresholds.add(searchResponse.getThresholds().get("1e-4"));
                thresholds.add(searchResponse.getThresholds().get("1e-3"));
                faces.add(searchResponse.getResults().get(0).getFaceToken());
            }

            @Override
            public void onFailed(String s) {
                LOG.error("MySearch fail,msg={}", s);
            }

        });
    }

    /**
     * 识别图片中的人体位置
     * 
     * @param filePath 图片路径
     * @param bodyRectangle 存放人体位置的容器
     */
    public static void myHumanBodySkeleton(String filePath, List<BodyRectangle> bodyRectangle) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(1);
        facePpApi.skeleton(params, LoadImage.getBytesFromFile(new File(filePath)),
            new IFaceCallBack<SkeletonResponse>() {

                @Override
                public void onSuccess(SkeletonResponse skeletonResponse) {
                    for (Skeleton skeleton : skeletonResponse.getSkeletons()) {
                        bodyRectangle.add(skeleton.getBodyRectangle());
                    }
                }

                @Override
                public void onFailed(String s) {
                    LOG.error("MyHumanBodySkeleton fail,msg={}", s);
                }

            });
    }

    /**
     * 把图片中的人体分离
     * 
     * @param filePath 图片路径
     * @param bufferedImage base64编码的图片
     */
    public static void myHumanBodySegment(String filePath, List<String> bufferedImage) {
        Map<String, String> params = Maps.newHashMapWithExpectedSize(1);
        /** 是否返回抠像后的灰度图 0：不返回灰度图 1：返回灰度图 */
        params.put("return_grayscale", "0");
        facePpApi.segment(params, LoadImage.getBytesFromFile(new File(filePath)),
            new IFaceCallBack<HumanSegmentResponse>() {

                @Override
                public void onSuccess(HumanSegmentResponse humanSegmentResponse) {
                    bufferedImage.add(humanSegmentResponse.getBodyImage());
                }

                @Override
                public void onFailed(String s) {
                    LOG.error("MyHumanBodySegment fail,msg={}", s);
                }

            });
    }

}
