package edu.yctc.face.util;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import edu.yctc.face.function.impl.FaceFunctionImpl;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.mapper.KnowledgeMapper;
import edu.yctc.project.system.knowledgeStudentState.mapper.KnowledgeStudentStateMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;


/**
 * 检测知识点线程
 *
 * @author 15272
 */
@ComponentScan({"edu.yctc.genesis.dao"})
public class CheckKnowledgeThread {

    private final static Logger LOG = LoggerFactory.getLogger("serviceLogger");

    /** 初始延迟 */
    private static final int INITIAL_DELAY = 0;

    /** 间隔时间 */
    private static final int SPLIT_TIME = 1;

    /** 检测最大时长 ：90分钟 */
    private static final long TIME_LIMIT = 5400000;

    /** 检测状态线程池 */
    public static final ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1,
        new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());

    // 手动注入
    private KnowledgeMapper knowledgeMapper =
        (KnowledgeMapper)SpringContextUtils.getBeanByClass(KnowledgeMapper.class);
    // 手动注入
    private KnowledgeStudentStateMapper knowledgeStudentStateMapper =
        (KnowledgeStudentStateMapper)SpringContextUtils.getBeanByClass(KnowledgeStudentStateMapper.class);
	// 手动注入
	private LessonMapper lessonMapper =
			(LessonMapper)SpringContextUtils.getBeanByClass(LessonMapper.class);

    /** 记录待检测知识点 */
    public static Map<Long, List<Knowledge>> LESSONID_2_KNOWLEDGE_MAP = new HashMap<>();

    /** 记录需要检测的知识点个数 */
    public static Map<Long, Integer> COUNT_2_LESSONID_MAP = new HashMap<>();

    /** 记录已经检测知识点的个数 */
    public static Map<Long, Integer> CURRENT_COUNT_2_LESSONID_MAP = new HashMap<>();

    /** 记录检测时间 */
    public static Map<Long, Date> TIME_2_LESSONID_MAP = new HashMap<>();

    /** 用于更新检测完成时间的知识点map */
    public static Map<Long, List<Knowledge>> KNOWLEDGE_LESSON_2_UPDATE_END_TIME = new HashMap<>();

    /**
     * 检测知识点
     *
     * @param lessonId
     */
    public void checkKnowledgeByLessonId(long lessonId) {
        executorService.scheduleWithFixedDelay(new Runnable() {

            @Override
            public void run() {
                if (LESSONID_2_KNOWLEDGE_MAP.containsKey(lessonId) == false
                    && COUNT_2_LESSONID_MAP.containsKey(lessonId) == false
                    && CURRENT_COUNT_2_LESSONID_MAP.containsKey(lessonId) == false) { // 首次检测
                    List<Knowledge> knowledgeDOs = new ArrayList<>();
                    // 获取知识点
	                Lesson lesson = lessonMapper.selectLessonById(lessonId);

	                Knowledge knowledge=new Knowledge();
	                knowledge.setLessonId(lessonId);
	                List<Knowledge> knowledges = knowledgeMapper.selectKnowledgeList(knowledge);

	                for (Knowledge knowledge1 : knowledges) {
                        if (knowledge1.getContent() != null) {
                            knowledgeDOs.add(knowledge1);
                        }
                    }
                    // 插入map
                    LESSONID_2_KNOWLEDGE_MAP.put(lessonId, knowledgeDOs);
                    COUNT_2_LESSONID_MAP.put(lessonId, knowledgeDOs.size());
                    CURRENT_COUNT_2_LESSONID_MAP.put(lessonId, 0);
                    TIME_2_LESSONID_MAP.put(lessonId, new Date());
                    LOG.info("first check, lessonId={}, count={}, currentCount=0", lessonId, knowledgeDOs.size());
                }
                // 获取知识点集
                List<Knowledge> knowledgeDOs = LESSONID_2_KNOWLEDGE_MAP.get(lessonId);
                if (knowledgeDOs.isEmpty()
                    || CURRENT_COUNT_2_LESSONID_MAP.get(lessonId) >= COUNT_2_LESSONID_MAP.get(lessonId)
                    || (System.currentTimeMillis() - TIME_2_LESSONID_MAP.get(lessonId).getTime()) >= TIME_LIMIT) {
                	// 无知识点待检测、检测次数到顶(知识点已经全部检测完)、超过检测时长最大值
                    try {
                        // 更新最后一个知识点完成检测时间
                        if (KNOWLEDGE_LESSON_2_UPDATE_END_TIME.containsKey(lessonId)
                            && !KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId).isEmpty()) {
                            while (!KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId).isEmpty()) {
	                            List<Knowledge> knowledges = KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId);
	                            Knowledge knowledge = knowledges.get(0);

	                           knowledge.setLessonId(lessonId);

	                            knowledgeMapper.updateKnowledge(knowledge);
	                            knowledges.remove(0);
                                KNOWLEDGE_LESSON_2_UPDATE_END_TIME.remove(lessonId);
                                KNOWLEDGE_LESSON_2_UPDATE_END_TIME.put(lessonId, knowledges);
                            }
                        }
                        if (knowledgeDOs.isEmpty()) {
                            LOG.info("no knowledge to check, lessonId={}", lessonId);
                        }
                        if (CURRENT_COUNT_2_LESSONID_MAP.get(lessonId) >= COUNT_2_LESSONID_MAP.get(lessonId)) {
                            LOG.info("check over, lessonId={}, count={}, currentCount={}", lessonId,
                                COUNT_2_LESSONID_MAP.get(lessonId), CURRENT_COUNT_2_LESSONID_MAP.get(lessonId));
                        }
                        if (System.currentTimeMillis()  - TIME_2_LESSONID_MAP.get(lessonId).getTime() >= TIME_LIMIT) {
                            LOG.info("check over, time limit, lessonId, count={}, currentCount={}", lessonId,
                                COUNT_2_LESSONID_MAP.get(lessonId), CURRENT_COUNT_2_LESSONID_MAP.get(lessonId));
                        }
                        LESSONID_2_KNOWLEDGE_MAP.remove(lessonId);
                        executorService.wait();
                    } catch (InterruptedException e) {
                        LOG.error("check knowledge thread wait exception, lessonId={}", lessonId, e);
                    }
                }
                // 有知识点待检测，调用checkKnowledge(long lessonId, String knowledge)
                FaceFunctionImpl faceFunctionImpl = new FaceFunctionImpl();
                for (Knowledge knowledgeDO : knowledgeDOs) { // 从待检测所有知识点中检索
                    String knowledge = knowledgeDO.getContent();
	                boolean check = false;
	                try {
		                check = faceFunctionImpl.checkKnowledge(String.valueOf(lessonId), knowledge);
	                } catch (IOException e) {
		                e.printStackTrace();
	                }
	                // 未检测到，继续检测
                    if (check == false) {
                        // 继续检测
                        continue;
                    } else if (check == true) {

	                    Knowledge knowledge1 = new Knowledge();
	                    knowledge1.setLessonId(lessonId);
	                    knowledge1.setContent(knowledge);
	                    List<Knowledge> knowledges = knowledgeMapper.selectKnowledgeList(knowledge1);
	                    // 状态检测
	                    try {
		                    faceFunctionImpl.checkStatusByLessonId(lessonId + "", knowledges.get(0).getId());
	                    } catch (IOException e) {
		                    e.printStackTrace();
	                    }
	                    // 更新上一次检测的结束时间
                        // 第一次检测到
                        if (!KNOWLEDGE_LESSON_2_UPDATE_END_TIME.containsKey(lessonId)) {
                            KNOWLEDGE_LESSON_2_UPDATE_END_TIME.put(lessonId, new ArrayList<>());
                        }
                        if (!KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId).isEmpty()) {
                            Knowledge knowledgeLessonDO =
                                KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId).get(0);
                            knowledgeMapper.updateKnowledge(knowledge1);
                            // 已经更新，从map删除
                            List<Knowledge> temp = KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId);
                            temp.remove(0);
                            KNOWLEDGE_LESSON_2_UPDATE_END_TIME.remove(lessonId);
                            KNOWLEDGE_LESSON_2_UPDATE_END_TIME.put(lessonId, temp);
                        }
                        // 插入待更新检测完成时间的队列
                        List<Knowledge> temp = KNOWLEDGE_LESSON_2_UPDATE_END_TIME.get(lessonId);
                        temp.add(knowledge1);
                        KNOWLEDGE_LESSON_2_UPDATE_END_TIME.remove(lessonId);
                        KNOWLEDGE_LESSON_2_UPDATE_END_TIME.put(lessonId, temp);
                        // 检测次数+1
                        int currentCount = CURRENT_COUNT_2_LESSONID_MAP.get(lessonId).intValue();
                        CURRENT_COUNT_2_LESSONID_MAP.remove(lessonId);
                        CURRENT_COUNT_2_LESSONID_MAP.put(lessonId, ++currentCount);
                        // 去除该知识点
                        knowledgeDOs.remove(knowledgeDO);
                        // 重新加入map
                        LESSONID_2_KNOWLEDGE_MAP.remove(lessonId);
                        LESSONID_2_KNOWLEDGE_MAP.put(lessonId, knowledgeDOs);
                        LOG.info("check knowledge success, knowledge={}, lessonId={}, count={}, currentCount={}",
                            knowledge, lessonId, COUNT_2_LESSONID_MAP.get(lessonId),
                            CURRENT_COUNT_2_LESSONID_MAP.get(lessonId));
                        break;
                    }
                }
            }
        }, INITIAL_DELAY, SPLIT_TIME, TimeUnit.SECONDS);
    }

    /**
     * 是否在检测
     *
     * @param lessonId
     * @return
     */
    public static boolean isChecking(long lessonId) {
        return LESSONID_2_KNOWLEDGE_MAP.containsKey(lessonId);
    }

}
