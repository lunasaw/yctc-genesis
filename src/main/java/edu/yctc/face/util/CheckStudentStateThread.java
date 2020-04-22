package edu.yctc.face.util;//package edu.yctc.face.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.yctc.common.exception.base.BaseException;
import edu.yctc.face.function.impl.FaceFunctionImpl;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



/**
 * 检测学生上课状态线程
 *
 * @author 15272
 */
public class CheckStudentStateThread {

    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** 一节课需要检测的次数 */
    private static final int EXCUTE_SUMMARY = 5;

    /** 间隔5分钟检测一次，单位为秒 */
    private static final int SPLIT_TIME = 5 * 60;

    /** 初始化延时 */
    private static final int INITIAL_DELAY = 0;

    /** 第一次检测 */
    private static final int FIRST_CHECK = 1;

    /** 检测状态线程池 */
    private static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    /** 用于记录检测次数的托管 */
    public static final Map<Long, Integer> EXCUTE_COUNT_2_LESSONID = new HashMap<>();

    /**
     * 检测学生状态线程
     *
     * @param lessonId
     * @return
     */
    public static final void checkStateByLessonId(long lessonId) {

        executorService.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                if (isExist(lessonId)) {
                    count(lessonId);
                } else {
                    addCount4LessonId(lessonId);
                }
                FaceFunctionImpl faceFunctionImpl = new FaceFunctionImpl();
	            try {
		            faceFunctionImpl.checkStatusByLessonId(Long.toString(lessonId));
	            } catch (IOException e) {
		            e.printStackTrace();
	            }
	            LOG.info("check state done for the #{} time, lessonId={}", getCount(lessonId), lessonId);
                if (getCount(lessonId) == EXCUTE_SUMMARY) {
                    LOG.info("check state already done for #{} times, lessonId={}", EXCUTE_SUMMARY, lessonId);
                    // 清除lessonId
                    clearLessonId(lessonId);
                    try {
                        // 等待下次被主动唤醒
                        executorService.wait();
                    } catch (InterruptedException e) {
                        LOG.error("check state thread wait exception, lessonId={}", lessonId, e);
                    }
                }
            }
        }, INITIAL_DELAY, SPLIT_TIME, TimeUnit.SECONDS);
    }

    /**
     * 添加lessonId对应的扫描次数
     *
     * @param lessonId
     */
    public static void addCount4LessonId(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("add count for lessonId fail, parameter invalid, lessonId={}", lessonId);
	        new BaseException("add count for lessonId fail");
        }
        EXCUTE_COUNT_2_LESSONID.put(lessonId, FIRST_CHECK);
    }

    /**
     * 判断lessonId是否存在于托管中
     *
     * @param lessonId
     * @return
     */
    public static boolean isExist(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("get key in holder fail, parameter invalid, lessonId={}", lessonId);
	        new BaseException("get key in holder fail");
        }
        return EXCUTE_COUNT_2_LESSONID.containsKey(lessonId);
    }

    /**
     * lessonId对应的检测次数加一
     *
     * @param lessonId
     */
    public static void count(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("count fail, parameter invalid, lessonId={}", lessonId);
	        new BaseException("count fail");
        }
        int count = EXCUTE_COUNT_2_LESSONID.get(lessonId);
        count++;
        EXCUTE_COUNT_2_LESSONID.remove(lessonId);
        EXCUTE_COUNT_2_LESSONID.put(lessonId, count);
    }

    /**
     * 获得lessonId对应的检测次数
     *
     * @param lessonId
     * @return
     */
    public static int getCount(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("get count fail, parameter invalid, lessonId={}", lessonId);
	        new BaseException("get count fail, parameter invalid");
        }
        if (isExist(lessonId) == false) {
            return 0;
        }
        return EXCUTE_COUNT_2_LESSONID.get(lessonId);
    }

    /**
     * 是否在检测
     *
     * @param lessonId
     * @return
     */
    public static boolean isChecking(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("initial fail, parameter invalid, lessonId={}", lessonId);
	        new BaseException("get count fail, parameter invalid");
        }
        if (EXCUTE_COUNT_2_LESSONID.containsKey(lessonId) == false) {
            return false;
        }
        return EXCUTE_COUNT_2_LESSONID.get(lessonId) != 0;
    }

    /**
     * lessonId清除
     *
     * @param lessonId
     */
    public static void clearLessonId(long lessonId) {
        if (lessonId <= 0) {
            LOG.error("clear lessonId fail, parameter invalid, lessonId={}", lessonId);
            return;
        }
        EXCUTE_COUNT_2_LESSONID.remove(lessonId);
        LOG.info("clear lessonId success, lessonId={}", lessonId);
    }
}
