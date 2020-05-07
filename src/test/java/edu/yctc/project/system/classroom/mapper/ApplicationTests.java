package edu.yctc.project.system.classroom.mapper;

import edu.yctc.project.system.attendance.domain.Attendance;
import edu.yctc.project.system.attendance.mapper.AttendanceMapper;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.classroom.mapper.ClassroomMapper;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.course.mapper.CourseMapper;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.mapper.StudentCoursestateMapper;
import edu.yctc.project.system.equipment.domain.Equipment;
import edu.yctc.project.system.equipment.mapper.EquipmentMapper;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.mapper.ClassScoreMapper;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.mapper.ClassroomStateMapper;
import edu.yctc.project.system.stu.domain.CourseStu;
import edu.yctc.project.system.stu.mapper.CourseStuMapper;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.mapper.LessonTeaMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
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

    // @Test
    public void dTest() throws ParseException {
        Course course = new Course();
        course.setClassesId("3");
        List<Course> courses = courseMapper.selectCourseList(course);
        // System.out.println(courses);
        Lesson lesson = new Lesson();
        Date date = null;
        Date end = null;
        Date date1 = new Date();
	    int j=10;
	    for (int i = 0; i < courses.size(); i++) {
            Course course1 = courses.get(i);
            lesson.setCourseId(course1.getId());
            lesson.setNumber(2L);

            if (course1.getTerm() == 0) {
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	            String s="2019-05-"+j+" 08:00:00";
	            date = df.parse(s);
	            s="2019-05-"+j+" 09:40:00";
	            end = df.parse(s);
	            j++;

                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                // LocalDateTime ldt = LocalDateTime.parse("2019-03-15 14:00:00", formatter);
                //
                // LocalDateTime end = LocalDateTime.parse("2019-03-15 14:40:00", formatter);
                //
                // ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
                // date = Date.from(zdt.toInstant());
                //
                // ZonedDateTime endd = end.atZone(ZoneId.systemDefault());
                // endds = Date.from(endd.toInstant());
            } else {

                DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String s="2018-12-"+j+" 08:00:00";
                date = df.parse(s);
	            s="2018-12-"+j+" 09:40:00";
                end = df.parse(s);
	            j++;
                // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                // LocalDateTime ldt = LocalDateTime.parse("2018-10-15 14:00:00", formatter);
                //
                // LocalDateTime end = LocalDateTime.parse("2018-10-15 14:40:00", formatter);
                //
                // ZonedDateTime zdt = ldt.atZone(ZoneId.systemDefault());
                // date = Date.from(zdt.toInstant());
                //
                // ZonedDateTime endd = end.atZone(ZoneId.systemDefault());
                // endds = Date.from(endd.toInstant());
            }
            lesson.setBegin(date);
            lesson.setEnd(end);
            lesson.setCreateTime(date1);
            lesson.setModifyTime(date1);
            lessonMapper.insertLesson(lesson);
        }
    }

    // @Test
    public void cTest() {
        Infost infost = new Infost();
        infost.setType(0L);
        infost.setClassId(3L);
        Course course = new Course();
        course.setClassesId("3");
        List<Infost> infosts = infostMapper.selectInfostList(infost);
        List<Course> courses = courseMapper.selectCourseList(course);
        List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
        Attendance attendance = new Attendance();
        Lesson lesson = new Lesson();
        Date date = new Date();
        for (int i = 0; i < infosts.size(); i++) {
            Infost infost1 = infosts.get(i);
            attendance.setUserId(infost1.getUserId());
            for (int i1 = 0; i1 < courses.size(); i1++) {
                String classesId = courses.get(i1).getClassesId();
                if (classesId.equals("3")) {
                     lesson.setCourseId(courses.get(i1).getId());
                    List<Lesson> lessons1 = lessonMapper.selectLessonList(lesson);

                    Long id = lessons1.get(0).getId();
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
    }

    // @Test
    public void aTest() {
        Infost infost = new Infost();
        infost.setType(0L);
        infost.setClassId(3L);
        List<Infost> infosts = infostMapper.selectInfostList(infost);
        // System.out.println(infosts);
        ClassScore classScore = new ClassScore();
        List<Course> courses = courseMapper.selectCourseList(new Course());
		Lesson lesson=new Lesson();
	    Date date = new Date();
	    List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
	    for (int i = 0; i < infosts.size(); i++) {
		    Infost infost1 = infosts.get(i);
		    classScore.setUserId(infost1.getUserId());
		    for (int i1 = 0; i1 < courses.size(); i1++) {
			    String classesId = courses.get(i1).getClassesId();
			    if (classesId.equals("3")) {
				    lesson.setCourseId(courses.get(i1).getId());
				    List<Lesson> lessons1 = lessonMapper.selectLessonList(lesson);
				    Long id = lessons1.get(0).getId();
				    classScore.setLessonId(id);
				    classScore.setScore(Double.valueOf(new Random().nextInt(50) + 50));
				    classScore.setModifyTime(date);
				    classScore.setCreateTime(date);
				    classScoreMapper.insertClassScore((classScore));
			    }

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

    // @Test
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

    @Resource
	StudentCoursestateMapper setScore;

    // @Test
	public void eTest() {
		Infost infost = new Infost();
		infost.setType(0L);
		infost.setClassId(3L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		// System.out.println(infosts);
		StudentCoursestate studentCoursestate=new StudentCoursestate();
		List<Course> courses = courseMapper.selectCourseList(new Course());
		Lesson lesson=new Lesson();
		Random random = new Random();
		Date date = new Date();
		List<Lesson> lessons = lessonMapper.selectLessonList(new Lesson());
		for (int i = 0; i < infosts.size(); i++) {
			Infost infost1 = infosts.get(i);
			studentCoursestate.setUserId(infost1.getUserId());
			for (int i1 = 0; i1 < courses.size(); i1++) {
				String classesId = courses.get(i1).getClassesId();
				if (classesId.equals("3")) {
					lesson.setCourseId(courses.get(i1).getId());
					List<Lesson> lessons1 = lessonMapper.selectLessonList(lesson);

					Long id = lessons1.get(0).getId();
					studentCoursestate.setScanStartTime(lessons1.get(0).getBegin());
					studentCoursestate.setScanEndTime(lessons1.get(0).getEnd());
					studentCoursestate.setLessonId(id);
					studentCoursestate.setState(Long.valueOf(rmain()));
					studentCoursestate.setFaceToken(infost1.getFaceToken());
					studentCoursestate.setModifyTime(date);
					studentCoursestate.setCreateTime(date);
					setScore.insertStudentCoursestate((studentCoursestate));

				}

			}

		}
	}

	public static int rmain() {
		int max=5;
		int min=1;
		Random random = new Random();

		int s = random.nextInt(max)%(max-min+1) + min;
		if (Math.random()>0.3){
			s=1;
		}

		return s;
	}

	@Resource
	EquipmentMapper equipmentMapper;

    // @Test
	public void fTest() {
		List<Classroom> classrooms = classroomMapper.selectClassroomList(new Classroom());
		Date date = new Date();
		for (int i = 0; i < classrooms.size(); i++) {
			Classroom classroom = classrooms.get(i);
			Equipment equipment=new Equipment();
			equipment.setId(Long.parseLong(classroom.getEquipmentId()));
			int size = equipmentMapper.selectEquipmentList(equipment).size();
			System.out.println(size);

				equipment.setVideo("http://123.206.53.234/yctc/test1.mp4");
				equipment.setNumber("1");
				equipment.setName("海康威视1-"+Long.parseLong(classroom.getEquipmentId()));
				equipment.setCreateTime(date);
				equipment.setModifyTime(date);
				equipmentMapper.insertEquipment(equipment);

		}
	}

	@Test
	public void gTest() {

		Infost infost = new Infost();
		infost.setType(1L);
		infost.setId(1L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		Infost infost2 = infosts.get(0);
		Infost infost1=new Infost();
		Course course=new Course();
		course.setClassesId("1");
		List<Course> courses = courseMapper.selectCourseList(course);
		Date date = new Date();
		for (int i = 0; i < courses.size(); i++) {
			infost.setAcademyId(1L);
			infost1.setType(1L);
			infost1.setIdCardNumber(infost2.getIdCardNumber()+i+1);
			Long i1 = Long.parseLong(infost2.getPhone());
			infost1.setPhone(String.valueOf(i1+i));
			infost1.setPicture(infost2.getPicture());
			infost1.setFaceToken(infost2.getFaceToken());
			infost1.setMail("10006"+i+"@qq.com");
			infost1.setCreateTime(date);
			infost1.setModifyTime(date);
			infostMapper.insertInfost(infost1);
		}
	}

	@Resource
	CourseStuMapper courseStuMapper;

    // @Test
	public void hTest() {
		Infost infost = new Infost();
		infost.setType(0L);
		infost.setClassId(3L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		// System.out.println(infosts);
		Course course = new Course();
		course.setClassesId("3");
		List<Course> courses = courseMapper.selectCourseList(course);
		Lesson lesson=new Lesson();
		Random random = new Random();
		Date date = new Date();
		CourseStu courseStu=new CourseStu();
		for (int i = 0; i < infosts.size(); i++) {
			for (int i1 = 0; i1 < courses.size(); i1++) {
				Long id = courses.get(i1).getId();
				Long userId = infosts.get(i).getUserId();
				courseStu.setUserId(userId);
				courseStu.setCourseId(id);
				courseStu.setCreateTime(date);
				courseStu.setModifyTime(date);
				courseStuMapper.insertCourseStu(courseStu);
			}

		}

	}

	@Resource
	LessonTeaMapper lessonTeaMapper;

    // @Test
	public void iTest() {
		Infost infost = new Infost();
		infost.setType(1L);
//		infost.setClassId(3L);
		List<Infost> infosts = infostMapper.selectInfostList(infost);
		// System.out.println(infosts);
		Course course = new Course();
//		course.setClassesId("3");
		course.setName("UML统一建模语言");
		List<Course> courses = courseMapper.selectCourseList(course);

		Lesson lesson=new Lesson();
		Random random = new Random();
		Date date = new Date();
		LessonTea lessonTea=new LessonTea();

		for (int i1 = 0; i1 < courses.size(); i1++) {
				Long id = courses.get(i1).getId();
			lesson.setCourseId(id);
			List<Lesson> lessons1 = lessonMapper.selectLessonList(lesson);

			lessonTea.setLessonId(lessons1.get(0).getId());
			lessonTea.setUserId(44L);
			lessonTea.setCreateTime(date);
			lessonTea.setModifyTime(date);
			lessonTeaMapper.insertLessonTea(lessonTea);
			}


	}
}
