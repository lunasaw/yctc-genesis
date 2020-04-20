package edu.yctc.project.system.score.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import edu.yctc.face.function.impl.FaceFunctionImpl;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.mapper.StudentCoursestateMapper;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.score.ClassScoreUtil;
import edu.yctc.project.system.stu.domain.CourseStu;
import edu.yctc.project.system.stu.mapper.CourseStuMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.score.mapper.ClassScoreMapper;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.service.IClassScoreService;
import edu.yctc.common.utils.text.Convert;

import javax.annotation.Resource;

/**
 * 学生上课评分汇总Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class ClassScoreServiceImpl implements IClassScoreService 
{
	private final static Logger log               = LoggerFactory.getLogger(ClassScoreServiceImpl.class);

	@Autowired
    private ClassScoreMapper classScoreMapper;

    /**
     * 查询学生上课评分汇总
     * 
     * @param id 学生上课评分汇总ID
     * @return 学生上课评分汇总
     */
    @Override
    public ClassScore selectClassScoreById(Long id)
    {
        return classScoreMapper.selectClassScoreById(id);
    }

    /**
     * 查询学生上课评分汇总列表
     * 
     * @param classScore 学生上课评分汇总
     * @return 学生上课评分汇总
     */
    @Override
    public List<ClassScore> selectClassScoreList(ClassScore classScore)
    {
        return classScoreMapper.selectClassScoreList(classScore);
    }

    /**
     * 新增学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    @Override
    public int insertClassScore(ClassScore classScore)
    {
        classScore.setCreateTime(DateUtils.getNowDate());
        return classScoreMapper.insertClassScore(classScore);
    }

    /**
     * 修改学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    @Override
    public int updateClassScore(ClassScore classScore)
    {
        return classScoreMapper.updateClassScore(classScore);
    }

    /**
     * 删除学生上课评分汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassScoreByIds(String ids)
    {
        return classScoreMapper.deleteClassScoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生上课评分汇总信息
     * 
     * @param id 学生上课评分汇总ID
     * @return 结果
     */
    @Override
    public int deleteClassScoreById(Long id)
    {
        return classScoreMapper.deleteClassScoreById(id);
    }

    @Resource
    LessonMapper lessonMapper;

    @Resource
    InfostMapper infostMapper;

    @Resource
    CourseStuMapper courseStuMapper;

    @Resource
    StudentCoursestateMapper studentCoursestateMapper;
	@Override
	public boolean calculateScoresByLessonId(long lessonId) {
		if (lessonId <= 0) {
			log.error("calculate scores by lessonId fail, parameter invalid, lessonId={}", lessonId);
			return false;
		}
		try {
			// 获取本堂课所有学生
			Lesson lesson = lessonMapper.selectLessonById(lessonId);
			if (lesson.getCourseId() <= 0) {
				log.error("get courseId by lessonId fail, courseId <= 0, lessonId={}, courseId={}", lessonId, lesson.getCourseId());
				return false;
			}
			// 得到选课学生id集
			CourseStu courseStu=new CourseStu();
			courseStu.setCourseId(lesson.getCourseId());
			List<CourseStu> courseStus = courseStuMapper.selectCourseStuList(courseStu);
			// 得到studentStateDO用于计算评分
			StudentCoursestate studentCoursestate = new StudentCoursestate();
			studentCoursestate.setLessonId(lessonId);

			for (CourseStu courseStu1 : courseStus) {
				studentCoursestate.setUserId(courseStu1.getUserId());
				List<StudentCoursestate> studentCoursestates = studentCoursestateMapper.selectStudentCoursestateList(studentCoursestate);
				if (studentCoursestates.isEmpty()) {
					log.error("no such stateDO, userId={}, lessonId={}", courseStu1.getUserId(), lessonId);
					continue;
				}
				Double aDouble = ClassScoreUtil.calculateScoreByState(studentCoursestates);
				if (aDouble ==null) {
					log.error("calculate score by state fail, userId={}, lessonId={}, stateDOs={}, resultDO={}", courseStu1.getUserId(),
							lessonId, studentCoursestates, aDouble);
					continue;
				}
				// 存入数据库，有则更新，无则增加
				ClassScore classScore=new ClassScore();
				classScore.setUserId(courseStu1.getUserId());
				classScore.setLessonId(lessonId);
				List<ClassScore> classScores = classScoreMapper.selectClassScoreList(classScore);
				ClassScore classScore1 = classScores.get(0);
				if (classScores != null) {
					classScore1.setScore(aDouble);
					classScoreMapper.updateClassScore(classScore1);
					log.info("calculate score success, update success, userId={}, lessonId={}, classScoreDO={}",  courseStu1.getUserId(),
							lessonId, classScore1);
				} else {
					ClassScore newClassScoreDO = new ClassScore();
					newClassScoreDO.setLessonId(lessonId);
					newClassScoreDO.setUserId(courseStu1.getUserId());
					newClassScoreDO.setScore(aDouble);
					classScoreMapper.insertClassScore(newClassScoreDO);
					log.info("calculate score success, insert success, userId={}, lessonId={}, classScoreDO={}", courseStu1.getUserId(),
							lessonId, newClassScoreDO);
				}
			}
			log.info("calculate score by lessonId success, lessonId={}, userIds={}", lessonId, courseStus);
			return true;
		} catch (Exception e) {
			log.error("calculate scores by lessonId error, lessonId={}", lessonId, e);
			return false;
		}
	}
}
