package edu.yctc.project.system.classroom.mapper;

import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.course.mapper.CourseMapper;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.mapper.KnowledgeMapper;
import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;
import edu.yctc.project.system.knowledgeStudentState.mapper.KnowledgeStudentStateMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @author Luna@win10
 * @date 2020/4/19 22:31
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class KnowledgeTest {

	@Resource
	KnowledgeStudentStateMapper knowledgeStudentStateMapper;

	@Resource
	LessonMapper lessonMapper;

	@Resource
	CourseMapper courseMapper;

	@Resource
	InfostMapper infostMapper;
	
	@Resource
	KnowledgeMapper knowledgeMapper;

	@Test
	public void aTest() throws ParseException {
		Infost infost = new Infost();
		infost.setType(0L);
		infost.setClassId(1L);
		KnowledgeStudentState knowledgeStudentState=new KnowledgeStudentState();
		Lesson lesson = new Lesson();
		lesson.setCourseId(2L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		Date date;
		Date end;
		Knowledge knowledge=new Knowledge();
		for (int j = 27; j <=40 ; j++) {
//			Knowledge knowledge1 = knowledgeMapper.selectKnowledgeById(Long.valueOf(j));
			Lesson lesson1 = lessonMapper.selectLessonById(583L);
			Date begin = lesson1.getBegin();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

			String format = df.format(begin);
			int minutes = begin.getMinutes();

			int month = begin.getMonth();
			int hours = begin.getHours();
			int day = begin.getDay();
			String s=2019+"-"+3+"-"+11+" "+hours+":"+minutes+(j-27)*8+":"+"00";

			date = df.parse(s);
			knowledgeStudentState.setScanStartTime(date);
			knowledgeStudentState.setCreateTime(date);
			String s1=2019+"-"+3+"-"+11+" "+hours+":"+minutes+(j-27)*8+7+":"+"00";
			end = df.parse(s1);


			for (int i = 0; i < infosts.size(); i++) {
				Long userId = infosts.get(i).getUserId();

				knowledgeStudentState.setScanEndTime(end);
				knowledgeStudentState.setModifyTime(end);
				knowledgeStudentState.setKnowledgeId(Long.valueOf(j));
				knowledgeStudentState.setUserId(userId);
				knowledgeStudentState.setLessonId(583L);
				knowledgeStudentState.setState(Integer.valueOf(rmain()));
				knowledgeStudentStateMapper.insertKnowledgeStudentState(knowledgeStudentState);
			}
		}

	}
	public static int rmain() {
		int max=5;
		int min=1;
		Random random = new Random();
		int s = random.nextInt(max)%(max-min+1) + min;
		if (s!=1 && Math.random()*10<5){
			s=1;
		}
		return s;
	}
}
