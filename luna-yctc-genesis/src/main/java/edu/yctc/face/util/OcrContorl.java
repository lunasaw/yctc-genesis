package edu.yctc.face.util;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Ocr文字检测接口
 * 
 * @author xiaotao
 */
public class OcrContorl {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /**
     * 检测图片上的文字
     * 
     * @param imgPath 图片路径
     * @return 图片上的字符
     */
    public static String ocrRecognise(String imgPath) {
        String valCode = null;
        try {
            valCode = new OcrRecognizer().recognizeText(new File(imgPath), "jpg");
        } catch (Exception e) {
            LOG.error("ocr error,e={}", e);
        }
        return valCode;
    }
}
