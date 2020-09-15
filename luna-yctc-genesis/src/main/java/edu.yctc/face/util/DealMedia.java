package edu.yctc.face.util;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 视频处理工具类
 * 
 * @author xiaotao
 */
public class DealMedia {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");
    /** 每秒帧数 */
    private static String frame = "1";
    /** 分辨率 */
    private static String resolutionRatio = "1920*1080";
    /** 视频生成总时长 */
    private static String totalTime = "04:28";

    /** 解析视频开始时间 */
    private static String startTime = "00:02";
    /** 解析视频结束时间 */
    private static String endTime = "04:30";

    /**
     * 获得每秒帧数
     * 
     * @return 每秒帧数
     */
    public static String getFrame() {
        return frame;
    }

    /**
     * 设置每秒帧数<br>
     * 默认为10帧每秒
     * 
     * @param frame 每秒帧数
     */
    public static void setFrame(String frame) {
        if (frame != null) {
            DealMedia.frame = frame;
        }
    }

    /**
     * 获得分辨率
     * 
     * @return 分辨率
     */
    public static String getResolutionRatio() {
        return resolutionRatio;
    }

    /**
     * 设置分辨率如 800*600<br>
     * 默认为1280*720
     * 
     * @param resolutionRatio 分辨率
     */
    public static void setResolutionRatio(String resolutionRatio) {
        if (resolutionRatio != null) {
            DealMedia.resolutionRatio = resolutionRatio;
        }
    }

    /**
     * 获得视频总时间
     * 
     * @return 视频总时间
     */
    public static String getTotalTime() {
        return totalTime;
    }

    /**
     * 设置视频总时间<br>
     * 默认为10秒
     * 
     * @param totalTime 视频总时间
     */
    public static void setTotalTime(String totalTime) {
        if (totalTime != null) {
            DealMedia.totalTime = totalTime;
        }
    }

    /**
     * 得到解析起始时间
     * 
     * @return 解析起始时间
     */
    public static String getStartTime() {
        return startTime;
    }

    /**
     * 设置解析起始时间<br>
     * 默认为00:00
     * 
     * @param startTime 解析起始时间
     */
    public static void setStartTime(String startTime) {
        if (startTime != null) {
            DealMedia.startTime = startTime;
        }
    }

    /**
     * 得到解析结束时间
     * 
     * @return 解析结束时间
     */
    public static String getEndTime() {
        return endTime;
    }

    /**
     * 设置解析结束时间<br>
     * 默认为00:10
     * 
     * @param endTime 解析结束时间
     */
    public static void setEndTime(String endTime) {
        if (endTime != null) {
            DealMedia.endTime = endTime;
        }
    }

    /**
     * 图片合成视频
     * 
     * @param inputPath 图片路径
     * @param outputPath 视频文件路径存储路径
     * @param ffmpegPath ffmpeg所在文件夹
     */
    public static void coding(String inputPath, String outputPath, String ffmpegPath) {
        List<String> command = getCodingCommand(inputPath, outputPath, ffmpegPath);
        if (null != command && command.size() > 0) {
            if (!process(command)) {
                LOG.error("Coding error, command={}", command);
            }
        }
    }

    /**
     * 视频变成图片
     * 
     * @param inputPath 视频路径
     * @param outputPath 图片存储路径
     * @param ffmpegPath ffmpeg所在文件夹
     */
    public static void decode(String inputPath, String outputPath, String ffmpegPath) {
        List<String> command = getDecodeCommand(inputPath, outputPath, ffmpegPath);
        if (null != command && command.size() > 0) {
            if (!process(command)) {
                LOG.error("decode error, command={}", command);
            }
        }
    }

    /**
     * 得到编码的ffmpeg命令
     * 
     * @param inputPath 图片路径
     * @param outputPath 视频文件路径存储路径
     * @param ffmpegPath ffmpeg所在文件夹
     * @return ffmpeg命令
     */
    private static List<String> getCodingCommand(String inputPath, String outputPath, String ffmpegPath) {
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath);
        command.add("-loop");
        command.add("0");
        /** 对输出文件进行覆盖 */
        command.add("-y");
        command.add("-f");
        command.add("image2");
        command.add("-r");
        /** 每秒帧数 */
        command.add(frame);
        command.add("-i");
        command.add(inputPath + "/%03d.jpg");
        command.add("-vcodec");
        command.add("libx264");
        command.add("-s");
        /** 分辨率 */
        command.add(resolutionRatio);
        command.add("-t");
        /** 视频生成总时长 */
        command.add(totalTime);
        command.add(outputPath);
        return command;
    }

    /**
     * 得到解码的ffmpeg命令
     * 
     * @param inputPath 视频路径
     * @param outputPath 图片存储路径
     * @param ffmpegPath ffmpeg所在文件夹
     * @return ffmpeg命令
     */
    private static List<String> getDecodeCommand(String inputPath, String outputPath, String ffmpegPath) {
        List<String> command = new ArrayList<String>();
        command.add(ffmpegPath);
        command.add("-ss");
        /** 起始时间 */
        command.add(startTime);
        command.add("-i");
        command.add(inputPath);
        command.add("-f");
        command.add("image2");
        command.add("-r");
        /** 帧数 */
        command.add(frame);
        command.add("-t");
        /** 终止时间 */
        command.add(endTime);
        command.add(outputPath + "/%03d.jpg");
        return command;
    }

    /**
     * ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
     * 
     * @param command ffmpeg命令
     * @return 执行是否成功
     */
    private static boolean process(List<String> command) {
        try {
            if (null == command || command.size() == 0) {
                return false;
            }

            Process videoProcess = new ProcessBuilder(command).redirectErrorStream(true).start();

            new PrintStream(videoProcess.getErrorStream()).start();
            new PrintStream(videoProcess.getInputStream()).start();

            int exitcode = videoProcess.waitFor();

            if (exitcode == 1) {
                return false;
            }
            return true;
        } catch (Exception e) {
            LOG.error("process execute failed!, Exception={}", e);
        }
        return false;
    }
}

/**
 * 信息打印流
 * 
 * @author xiaotao
 */
class PrintStream extends Thread {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    java.io.InputStream is = null;

    public PrintStream(java.io.InputStream is) {
        this.is = is;
    }

    @Override
    public void run() {
        try {
            while (this != null) {
                int ch = is.read();
                if (ch == -1) {
                    break;
                }
            }
        } catch (Exception e) {
            LOG.error("PrintStream execute failed!, Exception={}", e);
        }
    }
}
