package edu.yctc.project.applyClassroom;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.project.system.applyClassroom.domain.UserApplyclassroom;
import edu.yctc.project.system.applyClassroom.mapper.UserApplyclassroomMapper;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.classroom.mapper.ClassroomMapper;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.course.mapper.CourseMapper;
import edu.yctc.project.system.floor.domain.BuildingFloor;
import edu.yctc.project.system.floor.mapper.BuildingFloorMapper;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.mapper.ClassroomStateMapper;

/**
 * @author Luna@win10
 * @date 2020/4/21 16:38
 */
@Service("apply")
public class ApplyService {
    private final static Logger log = LoggerFactory.getLogger(ApplyService.class);

    @Resource
    private ClassroomMapper     classroomMapper;

    @Resource
    private LessonMapper        lessonMapper;

    @Resource
    private CourseMapper        courseMapper;

    @Resource
    BuildingFloorMapper         buildingFloorMapper;

    @Resource
    ClassroomStateMapper        classroomStateMapper;

    @Resource
    UserApplyclassroomMapper    userApplyclassroomMapper;

    /**
     * 获取信息检查后新增课程改变教室状态
     * 
     * @param applyVO
     * @return
     */
    public int addApply(ApplyVO applyVO) {
        // 检查楼层
        BuildingFloor buildingFloor = new BuildingFloor();
        buildingFloor.setBuilding(applyVO.getBuilding());
        buildingFloor.setFloor(applyVO.getFloor());
        List<BuildingFloor> buildingFloors = buildingFloorMapper.selectBuildingFloorList(buildingFloor);
        if (buildingFloors.size() == 0) {
            return 0;
        }
        log.info(buildingFloors.toString());
        // 检查教室
        Classroom classroom = new Classroom();
        classroom.setNumber(applyVO.getNumber());
        classroom.setBuildingFloorId(buildingFloors.get(0).getId());
        List<Classroom> classrooms = classroomMapper.selectClassroomList(classroom);
        if (classrooms.size() == 0) {
            return 0;
        }
        log.info(classrooms.toString());
        // 检查教室状态
        ClassroomState classroomState = new ClassroomState();
        classroomState.setClassroomId(classrooms.get(0).getId());
        List<ClassroomState> classroomStates = classroomStateMapper.selectClassroomStateList(classroomState);
        if (classroomStates.size() == 0) {
            classroomStateMapper.insertClassroomState(classroomState);
        }
        if (classroomStates.get(0).getState() == 1) {
            return 0;
        }
        log.info(classroomStates.toString());
        // 检查课程
        Course course = new Course();
        course.setName(applyVO.getName());
        course.setTerm(applyVO.getTerm());
        course.setYear(applyVO.getYear());
        List<Course> courses = courseMapper.selectCourseList(course);
        if (courses.size() == 0) {
            return 0;
        }
        log.info(courses.toString());

        // 插入课时
        Lesson lesson = new Lesson();
        lesson.setBegin(applyVO.getBegin());
        lesson.setEnd(applyVO.getEnd());
        lesson.setCourseId(courses.get(0).getId());
        lesson.setClassroomId(classrooms.get(0).getId());

        // TODO 这里也需要老师输入是第几次课时 后期通过该课时判断是本学期第几次课

        lesson.setNumber(2L);
        int i1 = lessonMapper.insertLesson(lesson);
        log.info(lesson.getId().toString() + "success");
        // 修改教室状态
        classroomStates.get(0).setState(1L);
        classroomStates.get(0).setLessonId(lesson.getId());
        int i = classroomStateMapper.updateClassroomState(classroomStates.get(0));

        // 添加纪录
        UserApplyclassroom userApplyclassroom = new UserApplyclassroom();
        userApplyclassroom.setClassroomId(classroomStates.get(0).getClassroomId());
        Long userId = ShiroUtils.getUserId();
        userApplyclassroom.setUserId(userId);
        userApplyclassroom.setLessonId(lesson.getId());

        userApplyclassroomMapper.insertUserApplyclassroom(userApplyclassroom);

        return i;
    }

    /**
     * 定时任务删除已经释放的教室
     */
    public void delRoomState() {
        Date date = new Date();
        List<ClassroomState> classroomStates = classroomStateMapper.selectClassroomStateList(new ClassroomState());
        for (int i = 0; i < classroomStates.size(); i++) {
            Long lessonId = classroomStates.get(i).getLessonId();
            Lesson lesson = lessonMapper.selectLessonById(lessonId);

            if (lesson!=null && lesson.getEnd() != null && lesson.getEnd() != null) {
                if (date.compareTo(lesson.getEnd()) == 1) {
                    ClassroomState classroomState = classroomStates.get(i);
                    classroomState.setState(0L);
                    classroomState.setLessonId(0L);
                    classroomStateMapper.updateClassroomState(classroomState);
                }
            }
        }
    }

}
