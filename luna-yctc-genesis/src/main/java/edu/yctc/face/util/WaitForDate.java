package edu.yctc.face.util;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 等待数据处理结果
 * 
 * @author Administrator
 */
public class WaitForDate {

    /** 日志 */
    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** 等待最长时间10秒 */
    private final static int WAITTIME = 10000;

    /**
     * 等待face++官网的处理结果
     * 
     * @param <T> 数据类型
     * @param data 需要处理的数据
     */
    public static <T> void waitForDate(List<T> data) {
        int time = 0;
        while (data.isEmpty() || time <= WAITTIME) {
            try {
                time += 100;
                Thread.sleep(100);
            } catch (InterruptedException e) {
                LOG.error("waitForDate Waiting for the failure, exception={}", e);
            }
        }
    }
}
