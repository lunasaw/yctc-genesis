package edu.yctc.face.util;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 摄像头调用接口
 * 
 * @author xiaotao
 */
public class CameraControl {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /**
     * 命令行参数意义： w 控制云台往上 s 控制云台往下 a 控制云台往左 d 控制云台往右 q 调焦（变大） e 调焦（变小） k 转到预置点 k后接上预置点号 u 开始巡航 u后接上巡航组号 i 结束巡航 i后接上巡航组号 n
     * 开始保存视频 n后接上时间单位毫秒
     */
    /**
     * 摄像头调用接口
     * 
     * @param sxtFilePath 摄像头exe文件路径
     * @param command 控制摄像头的命令
     */
    public static void execCommand(String sxtFilePath, String command) {
        try {
            Runtime.getRuntime().exec(sxtFilePath + ' ' + command);
        } catch (IOException e) {
            LOG.error("sxt error,e={}", e);
        }
    }
}
