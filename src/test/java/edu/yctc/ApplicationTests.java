package edu.yctc;

import edu.yctc.project.system.attendance.domain.Attendance;
import edu.yctc.project.system.attendance.mapper.AttendanceMapper;
import edu.yctc.project.system.classroom.mapper.ClassroomMapper;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.course.mapper.CourseMapper;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.mapper.ClassScoreMapper;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.mapper.ClassroomStateMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ApplicationTests {
    @Resource
    InfostMapper         infostMapper;

    @Resource
    ClassScoreMapper     classScoreMapper;

    @Resource
    LessonMapper         lessonMapper;

    @Resource
    CourseMapper         courseMapper;

    @Resource
    ClassroomMapper      classroomMapper;

    @Resource
    ClassroomStateMapper classroomStateMapper;

    @Resource
    AttendanceMapper     attendanceMapper;

    @Test
    public void cTest() {
        Infost infost = new Infost();
        infost.setType(0L);
        infost.setClassId(3L);
        List<Infost> infosts = infostMapper.selectInfostList(infost);
        List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
        Attendance attendance = new Attendance();
	    Date date = new Date();
	    for (int i = 0; i < infosts.size(); i++) {
            Infost infost1 = infosts.get(i);
            attendance.setUserId(infost1.getUserId());
            for (int i1 = 0; i1 < lessons.size(); i1++) {
                Long id = lessons.get(i1).getId();
                attendance.setLessonId(id);
                if (Math.random() * 10 < 1) {
                    attendance.setAttendState(1L);
                } else {
                    attendance.setAttendState(0L);
                }
                attendance.setModifyTime(date);
                attendance.setCreateTime(date);
                attendanceMapper.insertAttendance(attendance);
            }
        }
    }

    @Test
    public void aTest() {
        Infost infost = new Infost();
        infost.setType(0L);
        infost.setClassId(3L);
        List<Infost> infosts = infostMapper.selectInfostList(infost);
        // System.out.println(infosts);
        ClassScore classScore = new ClassScore();
        List<Course> courses = courseMapper.selectCourseList(new Course());

        List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
        Date date = new Date();
        System.out.println(date);
        for (int i = 0; i < lessons.size(); i++) {
            for (int j = 0; j < infosts.size(); j++) {
                Long userId = infosts.get(j).getUserId();
                classScore.setUserId(userId);
                classScore.setLessonId(lessons.get(i).getId());
                classScore.setScore(Double.valueOf(new Random().nextInt(50) + 50));
                classScore.setCreateTime(date);
                classScore.setModifyTime(date);
                classScoreMapper.insertClassScore(classScore);
            }
        }
        Date date1 = new Date();
        System.out.println(date1);

    }

    public static int random() {
        int max = 20;
        int min = 10;
        Random random = new Random();

        int s = random.nextInt(max) % (max - min + 1) + min;
        return s;
    }

    @Test
    public void bTest() {
        List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
        ClassroomState classroomState = new ClassroomState();
        for (int i = 0; i < lessons.size(); i++) {
            Long classroomId = lessons.get(i).getClassroomId();
            classroomState.setClassroomId(classroomId);
            classroomState.setLessonId(lessons.get(i).getId());
            // if (Math.random()*10<1){
            // classroomState.setState(0L);
            // }else {
            // classroomState.setState(1L);
            // }
            classroomState.setState(1L);
            classroomStateMapper.insertClassroomState(classroomState);
        }
    }

}
