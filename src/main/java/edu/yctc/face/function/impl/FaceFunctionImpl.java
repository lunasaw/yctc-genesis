package edu.yctc.face.function.impl;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import com.google.common.collect.ImmutableMap;
import edu.yctc.common.utils.file.Base64Utils;
import edu.yctc.common.utils.file.FileUtils;
import edu.yctc.common.utils.file.FileUtilsAlter;
import edu.yctc.common.utils.http.HttpUtils;
import edu.yctc.common.utils.http.HttpUtilsLuna;
import edu.yctc.face.OcrBaiduApi;
import edu.yctc.face.function.FaceFunction;
import edu.yctc.face.util.*;
import edu.yctc.project.system.academy.domain.Academy;
import edu.yctc.project.system.academy.service.IAcademyService;
import edu.yctc.project.system.academy.service.impl.AcademyServiceImpl;
import edu.yctc.project.system.attendance.attendance.AttendState;
import edu.yctc.project.system.attendance.domain.Attendance;
import edu.yctc.project.system.attendance.service.IAttendanceService;
import edu.yctc.project.system.attendance.service.impl.AttendanceServiceImpl;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.classroom.service.IClassroomService;
import edu.yctc.project.system.classroom.service.impl.ClassroomServiceImpl;
import edu.yctc.project.system.course.service.impl.CourseServiceImpl;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.service.IStudentCoursestateService;
import edu.yctc.project.system.courseState.service.impl.StudentCoursestateServiceImpl;
import edu.yctc.project.system.equipment.domain.Equipment;
import edu.yctc.project.system.equipment.service.IEquipmentService;
import edu.yctc.project.system.equipment.service.impl.EquipmentServiceImpl;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.service.IInfostService;
import edu.yctc.project.system.infost.service.impl.InfostServiceImpl;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;
import edu.yctc.project.system.knowledge.service.impl.KnowledgeServiceImpl;
import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;
import edu.yctc.project.system.knowledgeStudentState.service.IKnowledgeStudentStateService;
import edu.yctc.project.system.knowledgeStudentState.service.impl.KnowledgeStudentStateServiceImpl;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.service.ILessonService;
import edu.yctc.project.system.lesson.service.impl.LessonServiceImpl;
import edu.yctc.project.system.score.service.IClassScoreService;
import edu.yctc.project.system.score.service.impl.ClassScoreServiceImpl;
import edu.yctc.project.system.stu.domain.CourseStu;
import edu.yctc.project.system.stu.service.ICourseStuService;
import edu.yctc.project.system.stu.service.impl.CourseStuServiceImpl;
import edu.yctc.project.system.user.service.UserServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

/**
 * 人脸识别相关功能
 *
 * @author xiaotao
 */
public class FaceFunctionImpl implements FaceFunction {

    /** 日志 */
    private final static Logger           log                          =
        LoggerFactory.getLogger(FaceFunctionImpl.class);

    /** 循环次数 */
    private int                           cycleIndex                   = 1;

    final static String                   pathexe                      = "D:\\ffmpeg\\ffmpeg\\bin\\ffmpeg.exe";

    /** service层接口 */
    /** 用户档案 */
    private IInfostService                infostService                =
        (InfostServiceImpl)SpringContextUtils.getBeanByClass(InfostServiceImpl.class);
    /** 学院 */
    private IAcademyService               academyService               =
        (AcademyServiceImpl)SpringContextUtils.getBeanByClass(AcademyServiceImpl.class);
    /** 知识点 */
    private IKnowledgeService             knowledgeService             =
        (KnowledgeServiceImpl)SpringContextUtils.getBeanByClass(KnowledgeServiceImpl.class);
    /** 课时间 */
    private ILessonService                lessonService                =
        (LessonServiceImpl)SpringContextUtils.getBeanByClass(LessonServiceImpl.class);
    /** 课程-学生 */
    private ICourseStuService             courseStuService             =
        (CourseStuServiceImpl)SpringContextUtils.getBeanByClass(CourseStuServiceImpl.class);
    /** 教室 */
    private IClassroomService             classroomService             =
        (ClassroomServiceImpl)SpringContextUtils.getBeanByClass(ClassroomServiceImpl.class);
    /** 设备 */
    private IEquipmentService             equipmentService             =
        (EquipmentServiceImpl)SpringContextUtils.getBeanByClass(EquipmentServiceImpl.class);
    /** 状态 */
    private IAttendanceService            attendanceService            =
        (AttendanceServiceImpl)SpringContextUtils.getBeanByClass(AttendanceServiceImpl.class);
    /** 课程评分 */
    private IClassScoreService            scoreService                 =
        (ClassScoreServiceImpl)SpringContextUtils.getBeanByClass(ClassScoreServiceImpl.class);
    /** 学生状态 */
    private IStudentCoursestateService    iStudentCoursestateService   =
        (StudentCoursestateServiceImpl)SpringContextUtils.getBeanByClass(StudentCoursestateServiceImpl.class);
    /** 知识点状态 */
    private IKnowledgeStudentStateService knowledgeStudentStateService =
        (KnowledgeStudentStateServiceImpl)SpringContextUtils.getBeanByClass(KnowledgeStudentStateServiceImpl.class);

    @Override
    public void alterFaceToken(Infost infost) {
        /** 存放人脸 */
        List<Face> faces = new ArrayList<>();

        /** 识别 */
        MyFaceApi.myDetectFromUrl(infost.getPicture(), null, faces);

        /** 等待人脸检测完成 */
        WaitForDate.waitForDate(faces);

        /** 保存到face++平台的faceset */
        List<Infost> infosts = infostService.selectInfostList(infost);
        Academy academy = academyService.selectAcademyById(infosts.get(0).getAcademyId());
        if (academy.getAcademy() != null) {
            MyFaceApi.myFaceSetAdd(academy.getAcademy(), faces.get(0).getFaceToken());
        } else {
            log.error("Colleges don't exist!, user={}", infost);
            return;
        }

        /** 更新数据库的face_token */
        infost.setFaceToken(faces.get(0).getFaceToken());
        infostService.updateInfost(infost);

    }

    @Override
    public void dealMedia(String videoPath, String savePath, String startTime, String endTime, String totalTime,
        String frame, String resolutionRatio) {
        /** 设置相关参数参数 */
        DealMedia.setTotalTime(totalTime);
        DealMedia.setFrame(frame);
        DealMedia.setResolutionRatio(resolutionRatio);
        DealMedia.setStartTime(startTime);
        DealMedia.setEndTime(endTime);
        /** 视频解析成图片 */
        DealMedia.decode(videoPath, "src\\img", pathexe);

        /** 处理图片 */
        try {
            List<String> fileNames = new ArrayList<>();
            GetFoldFileNames.getFileName(fileNames, "src\\img");
            for (String temp : fileNames) {
                System.out.println("src\\img\\" + temp);
                DealImage.paint("src\\img\\" + temp);
            }
        } catch (Exception e) {
            log.error("img is not exist!, exception={}", e);
        }

        /** 图片合成视频 */
        DealMedia.coding("src\\img", savePath, pathexe);
    }

    @Override
    public void cameraControl(String command) {
        CameraControl.execCommand("C:\\src\\sxt\\sxt.exe", command);
    }

    @Override
    public String ocrControl(String imgPath) {
        return OcrContorl.ocrRecognise(imgPath);
    }

    @Override
    public void checkByLessonId(Long lessonId, String image) throws IOException {
        /** 得到所有学生 */
        Lesson lesson = lessonService.selectLessonById(lessonId);
        CourseStu courseStu = new CourseStu();
        courseStu.setCourseId(lesson.getCourseId());
        /** 课时查所对应课程 查选修该课程的学生 */
        List<CourseStu> courseStus = courseStuService.selectCourseStuList(courseStu);
        List<Infost> list = new ArrayList<>();
        for (int i = 0; i < courseStus.size(); i++) {
            Infost infost = infostService.selectInfostById(courseStus.get(i).getUserId());
            list.add(infost);
        }
        if (list.size() == 0) {
            log.error("There are no students in the course, lessonId={}", lessonId);
            return;
        }
        // for (int i = 0; i < list.size(); i++) {
        // log.info("课程人员:" + list.get(i).getName());
        // }
        /** 得到视频地址 */
        Classroom classroom = classroomService.selectClassroomById(lesson.getClassroomId());
        Equipment equipment = equipmentService.selectEquipmentById(Long.valueOf(classroom.getEquipmentId()));
        if (equipment == null) {
            log.error("Camera information acquisition failed, lessonId={}", lessonId);
            return;
        }
        log.info("视频地址:" + equipment.getVideo());
        // TODO 添加通过设备id找到视频播放地址 暂为本地地址
        String video = equipment.getVideo();
        /** 存放图片中的人脸 */
        List<Face> faces = new ArrayList<>();
        /** 存放需要添加的AttendanceDO */
        List<Attendance> attendanceDOs = new ArrayList<>();

        // TODO 换成视频URL

        for (int i = 1; i <= cycleIndex; ++i) {
            faces.clear();
            // TODO 图片改回来

            String path = ResourceUtils.getURL("classpath:static/").getPath();
            File file = new File(path + "tmp.jpg");
            if (file.exists()) {
                FileUtils.deleteFile(path + "tmp.jpg");
            }
            if (HttpUtilsLuna.isNetUrl(image)) {
                FileUtilsAlter.downloadHttpUrl(image, path, "tmp.jpg");
            }
            log.info("获取截图");
            MyFaceApi.myDetect(path + "tmp.jpg", null, faces);

            log.info("等待人脸检测完成");
            WaitForDate.waitForDate(faces);

            /** 用来和数据库里面每个人的face_token对比 */
            /** 搜索faceset中最像的face_token */
            List<String> searthFaceToken = new ArrayList<>();
            /** 置信值和阈值 */
            List<Float> thresholds = new ArrayList<>();

            log.info("搜索faceset中最佳匹配");
            for (Face face : faces) {
                searthFaceToken.clear();
                thresholds.clear();
                Long academyId = list.get(0).getAcademyId();
                if (academyId != null) {
                    Academy academy = academyService.selectAcademyById(academyId);
                    MyFaceApi.mySearch(face.getFaceToken(), academy.getAcademy(), thresholds, searthFaceToken);
                } else {
                    log.error("Colleges don't exist!, infost={}", list);
                }

                /** 等待人脸搜索完成 */
                WaitForDate.waitForDate(searthFaceToken);

                if (thresholds.get(0) > thresholds.get(3)) {
                    boolean flag = false;
                    Infost infost = new Infost();
                    infost.setFaceToken(searthFaceToken.get(0));
                    List<Infost> list1 = infostService.selectInfostList(infost);
                    if (list1.size() == 0) {
                        continue;
                    }

                    for (Infost temp : list) {
                        // log.info(temp.getName() + "对比" + list1.get(0).getName());
                        if (temp.getId().equals(list1.get(0).getId())) {
                            flag = true;
                            log.info("在场:" + temp.getName());
                        }
                        /** 在所有学生里面 */
                        Attendance attendance = new Attendance();
                        if (flag) {
                            attendance.setUserId(list1.get(0).getId());
                            attendance.setLessonId(lessonId);
                            attendance.setAttendState(Long.valueOf(AttendState.ATTENDANCE));
                            attendanceDOs.add(attendance);
                        } else {
                            attendance.setUserId(list1.get(0).getId());
                            attendance.setLessonId(lessonId);
                            attendance.setAttendState(Long.valueOf(AttendState.ABSENT));
                            log.error("课程中不存在此人 name={}", list1.get(0).getName());
                        }
                    }
                }
                thresholds.clear();
            }
        }

        /** 去重添加到数据库 */
        List<Attendance> newLists = removeRepetitiveAttendanceDO(attendanceDOs);
        for (Attendance attendance : newLists) {
            attendanceService.insertAttendance(attendance);
        }
    }

    @Override
    public void checkStatusByLessonId(String lessonId) throws IOException {
        /** 得到所有学生 */
        Lesson lesson = lessonService.selectLessonById(Long.parseLong(lessonId));
        CourseStu courseStu = new CourseStu();
        courseStu.setCourseId(lesson.getCourseId());
        /** 课时查所对应课程 查选修该课程的学生 */
        List<CourseStu> courseStus = courseStuService.selectCourseStuList(courseStu);
        List<Infost> list = new ArrayList<>();
        for (int i = 0; i < courseStus.size(); i++) {
            Infost infost = infostService.selectInfostById(courseStus.get(i).getUserId());
            list.add(infost);
        }
        if (list.size() == 0) {
            log.error("There are no students in the course, lessonId={}", lessonId);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            log.info("课程人员:" + list.get(i).getName());
        }

        /** 得到视频地址 */
        Classroom classroom = classroomService.selectClassroomById(lesson.getClassroomId());
        Equipment equipment = equipmentService.selectEquipmentById(Long.valueOf(classroom.getEquipmentId()));
        if (equipment == null) {
            log.error("Camera information acquisition failed, lessonId={}", lessonId);
            return;
        }
        log.info("视频地址:" + equipment.getVideo());
        // TODO 添加通过设备id找到视频播放地址 暂为本地地址
        String video = equipment.getVideo();

        /** 照片中的人数 */
        int peopleNumber = 0;
        /** 存放图片中的人脸 */
        List<Face> faces = new ArrayList<>();
        /** 存放需要添加的Infost */
        ArrayList<StudentCoursestate> studentCoursestates = new ArrayList<>();

        // TODO 换成视频URL
        /**
         * dealMedia("C:\\src\\mp4\\1.mp4", "C:\\src\\mp4\\test.mp4", "00:00", "00:10", "10", "10", null);
         */
	    String path = ResourceUtils.getURL("classpath:static/").getPath();

        for (int i = 1; i <= cycleIndex; ++i) {
            // TODO 图片改回来
            String imgPath = "http://iszychen.club:8087/iszychen/img/genesis/lesson/img/1.jpg";
	        File file=new File(path+"tmp.jpg");
	        if (file.exists()) {
		        FileUtils.deleteFile(path + "tmp.jpg");
	        }
	        if (HttpUtilsLuna.isNetUrl(imgPath)) {
		        FileUtilsAlter.downloadHttpUrl(imgPath, path, "tmp.jpg");
	        }
            /** 把图片中的背景去掉 */
            removeTheBackground(path + "tmp.jpg");
            /** 转换png图片格式为jpg */
            formatConversion(path + "tmp.jpg");
            /** 把图片中的人单独截图出来 */
            peopleNumber = cutPicture(path + "tmp.jpg");

            /** 用来和数据库里面每个人的face_token对比 */
            /** 搜索faceset中最像的face_token */
            List<String> searthFaceToken = new ArrayList<>();
            /** 置信值和阈值 */
            List<Float> thresholds = new ArrayList<>();

            for (int j = 0; j < peopleNumber; ++j) {
            	String ps="http://iszychen.club:8087/iszychen/img/genesis/lesson/"+lessonId+"/img/"+j+".jpg";
                faces.clear();
                searthFaceToken.clear();
                thresholds.clear();
	            String s=path+"tmp.jpg";
	            if (file.exists()) {
		            FileUtils.deleteFile(path + "tmp.jpg");
	            }
	            if (HttpUtilsLuna.isNetUrl(ps)) {
		            FileUtilsAlter.downloadHttpUrl(ps, path, "tmp.jpg");
	            }

                MyFaceApi.myDetect("tmp.jpg", null, faces);

                /** 等待人脸检测完成 */
                WaitForDate.waitForDate(faces);

                Academy academy = academyService.selectAcademyById(list.get(0).getAcademyId());
                if (academy != null) {
                    MyFaceApi.mySearch(faces.get(0).getFaceToken(), academy.getAcademy(), thresholds,
                        searthFaceToken);
                } else {
                    log.error("Colleges don't exist!, infost={}", list.get(0).getName());
                }

                /** 等待人脸搜索完成 */
                WaitForDate.waitForDate(searthFaceToken);

                if (thresholds.get(0) > thresholds.get(3)) {
                    boolean flag = false;
                    Infost infost = new Infost();
                    infost.setFaceToken(searthFaceToken.get(0));
                    List<Infost> list1 = infostService.selectInfostList(infost);

                    if (list1.size() == 0) {
                        continue;
                    }

                    for (Infost temp : list) {
                        if (temp.getId().equals(list.get(0).getId())) {
                            flag = true;
                        }

                        /** 在所有学生里面 */
                        if (flag) {
                            /** 用python模型进行状态检测 */
                            int state = Model.dealPicture("D:\\ffmpeg\\img\\" + j + ".jpg");
                            StudentCoursestate studentStateDO = new StudentCoursestate();
                            studentStateDO.setUserId(list1.get(0).getId());
                            studentStateDO.setLessonId(Long.parseLong(lessonId));
                            studentStateDO.setState(Long.valueOf(state));
                            studentStateDO.setFaceToken(searthFaceToken.get(0));
                            studentCoursestates.add(studentStateDO);

                            // 发短信提醒
                            // if (state != StudentState.LISTENING) {
                            // messageService.sendSms(userDO.getModule().getPhone(),
                            // ConstantHolder.STATE_SMS_TITLE + userDO.getModule().getName()
                            // + ConstantHolder.STATE_SMS_CONTENT);
                            // }
                        } else {
                            log.error("课程中不存在此人 info={}", list1.get(0).getName());
                        }
                    }
                }
                thresholds.clear();
            }
        }

        /** 去重添加到数据库 */
        List<StudentCoursestate> newLists = removeRepetitiveStudentStateDO(studentCoursestates);
        for (StudentCoursestate studentStateDO : newLists) {
            iStudentCoursestateService.insertStudentCoursestate(studentStateDO);
        }

        // 计算课堂评分
        scoreService.calculateScoresByLessonId(Long.parseLong(lessonId));
    }

    @Override
    public void checkStatusByLessonId(String lessonId, long knowledge) throws IOException {
        /** 得到所有学生 */
        Lesson lesson = lessonService.selectLessonById(Long.parseLong(lessonId));
        CourseStu courseStu = new CourseStu();
        courseStu.setCourseId(lesson.getCourseId());
        /** 课时查所对应课程 查选修该课程的学生 */
        List<CourseStu> courseStus = courseStuService.selectCourseStuList(courseStu);
        List<Infost> list = new ArrayList<>();
        for (int i = 0; i < courseStus.size(); i++) {
            Infost infost = infostService.selectInfostById(courseStus.get(i).getUserId());
            list.add(infost);
        }
        if (list.size() == 0) {
            log.error("There are no students in the course, lessonId={}", lessonId);
            return;
        }
        // for (int i = 0; i < list.size(); i++) {
        // log.info("课程人员:" + list.get(i).getName());
        // }

        /** 得到视频地址 */
        Classroom classroom = classroomService.selectClassroomById(lesson.getClassroomId());
        Equipment equipment = equipmentService.selectEquipmentById(Long.valueOf(classroom.getEquipmentId()));
        if (equipment == null) {
            log.error("Camera information acquisition failed, lessonId={}", lessonId);
            return;
        }
        log.info("视频地址:" + equipment.getVideo());
        // TODO 添加通过设备id找到视频播放地址 暂为本地地址
        String video = equipment.getVideo();

        /** 照片中的人数 */
        int peopleNumber = 0;
        /** 存放图片中的人脸 */
        List<Face> faces = new ArrayList<>();
        /** 存放需要添加的KnowledgeStudentStateDO */
        ArrayList<KnowledgeStudentState> knowledgeStudentStateDOs = new ArrayList<>();

        // TODO 换成视频URL
        /**
         * dealMedia("C:\\src\\mp4\\1.mp4", "C:\\src\\mp4\\test.mp4", "00:00", "00:10", "10", "10", null);
         */
	    String path = ResourceUtils.getURL("classpath:static/").getPath();

        for (int i = 1; i <= cycleIndex; ++i) {
            // TODO 图片改回来
            String imgPath = "D:\\ffmpeg\\img\\001.jpg";

	        File file=new File(path+"tmp.jpg");

            /** 把图片中的背景去掉 */
            removeTheBackground(imgPath);
            /** 转换png图片格式为jpg */
            formatConversion(imgPath);
            /** 把图片中的人单独截图出来 */
            peopleNumber = cutPicture(imgPath);

            /** 用来和数据库里面每个人的face_token对比 */
            /** 搜索faceset中最像的face_token */
            List<String> searthFaceToken = new ArrayList<>();
            /** 置信值和阈值 */
            List<Float> thresholds = new ArrayList<>();

            for (int j = 0; j < peopleNumber; ++j) {
                faces.clear();
                searthFaceToken.clear();
                thresholds.clear();
	            String ps="http://iszychen.club:8087/iszychen/img/genesis/lesson/"+lessonId+"/img/"+j+".jpg";

	            if (file.exists()) {
		            FileUtils.deleteFile(path + "tmp.jpg");
	            }
	            if (HttpUtilsLuna.isNetUrl(ps)) {
		            FileUtilsAlter.downloadHttpUrl(ps, path, "tmp.jpg");
	            }
                MyFaceApi.myDetect(path+"tmp.jpg", null, faces);

                /** 等待人脸检测完成 */
                WaitForDate.waitForDate(faces);

                Academy academy = academyService.selectAcademyById(list.get(0).getAcademyId());
                if (academy != null) {
                    MyFaceApi.mySearch(faces.get(0).getFaceToken(), academy.getAcademy(), thresholds,
                        searthFaceToken);
                } else {
                    log.error("Colleges don't exist!, infost={}", list.get(0).getName());
                }

                /** 等待人脸搜索完成 */
                WaitForDate.waitForDate(searthFaceToken);

                if (thresholds.get(0) > thresholds.get(3)) {
                    boolean flag = false;
                    Infost infost = new Infost();
                    infost.setFaceToken(searthFaceToken.get(0));
                    List<Infost> list1 = infostService.selectInfostList(infost);

                    if (list1.size() == 0) {
                        continue;
                    }

                    for (Infost temp : list) {
                        if (temp.getId().equals(list.get(0).getId())) {
                            flag = true;
                        }

                        /** 在所有学生里面 */
                        if (flag) {
                            /** 用python模型进行状态检测 */
                            int state = Model.dealPicture("D:\\ffmpeg\\img\\" + j + ".jpg");
                            KnowledgeStudentState knowledgeStudentStateDO = new KnowledgeStudentState();
                            knowledgeStudentStateDO.setUserId(list1.get(0).getUserId());
                            knowledgeStudentStateDO.setLessonId(Long.parseLong(lessonId));
                            knowledgeStudentStateDO.setState(state);
                            knowledgeStudentStateDO.setKnowledgeId(knowledge);
                            knowledgeStudentStateDOs.add(knowledgeStudentStateDO);
                            // 发短信提醒
                            // if (state != StudentState.LISTENING) {
                            // messageService.sendSms(userDO.getModule().getPhone(),
                            // ConstantHolder.STATE_SMS_TITLE + userDO.getModule().getName()
                            // + ConstantHolder.STATE_SMS_CONTENT);
                            // }
                        } else {
                            log.error("课程中不存在此人 infost={}", list1.get(0).getName());
                        }
                    }
                }
                thresholds.clear();
            }
        }

        /** 去重添加到数据库 */
        List<KnowledgeStudentState> newLists = removeRepetitiveKnowledgeStudentStateDO(knowledgeStudentStateDOs);
        for (KnowledgeStudentState knowledgeStudentStateDO : newLists) {
            knowledgeStudentStateService.insertKnowledgeStudentState(knowledgeStudentStateDO);
        }
    }

    /**
     * 去除重复的AttendanceDO
     *
     * @param attendanceDOs AttendanceDO列表
     * @return 去重后的AttendanceDO列表
     */
    private List<Attendance> removeRepetitiveAttendanceDO(List<Attendance> attendanceDOs) {
        List<Attendance> newLists = new ArrayList<>();
        boolean flag = true;
        for (Attendance attendanceDO : attendanceDOs) {
            for (Attendance tempStudentStateDO : newLists) {
                if (tempStudentStateDO.getUserId().equals(attendanceDO.getUserId())) {
                    flag = false;
                }
            }
            if (flag) {
                newLists.add(attendanceDO);
            }
            flag = true;
        }
        return newLists;
    }

    /**
     * 去除重复的StudentStateDO
     *
     * @param studentStateDOs StudentStateDO列表
     * @return 去重后的StudentStateDO列表
     */
    private List<StudentCoursestate> removeRepetitiveStudentStateDO(List<StudentCoursestate> studentStateDOs) {
        List<StudentCoursestate> newLists = new ArrayList<>();
        boolean flag = true;
        for (StudentCoursestate studentStateDO : studentStateDOs) {
            for (StudentCoursestate tempStudentStateDO : newLists) {
                if (tempStudentStateDO.getUserId().equals(studentStateDO.getUserId())) {
                    flag = false;
                }
            }

            if (flag) {
                newLists.add(studentStateDO);
            }

            flag = true;
        }
        return newLists;
    }

    /**
     * 去除重复的StudentStateDO
     *
     * // * @param studentStateDOs StudentStateDO列表
     * 
     * @return 去重后的StudentStateDO列表
     */
    private List<KnowledgeStudentState>
        removeRepetitiveKnowledgeStudentStateDO(List<KnowledgeStudentState> knowledgeStudentStateDOs) {
        List<KnowledgeStudentState> newLists = new ArrayList<>();
        boolean flag = true;
        for (KnowledgeStudentState knowledgeStudentStateDO : knowledgeStudentStateDOs) {
            for (KnowledgeStudentState tempKnowledgeStudentStateDO : newLists) {
                if (tempKnowledgeStudentStateDO.getUserId().equals(knowledgeStudentStateDO.getUserId())) {
                    flag = false;
                }
            }

            if (flag) {
                newLists.add(knowledgeStudentStateDO);
            }

            flag = true;
        }
        return newLists;
    }

    /**
     * 去除图片中的背景 图片大小不变 返回的是png格式
     *
     * // * @param inputPath 图片地址
     */
    private void removeTheBackground(String imgPath) {
        /** base64解码器 */
        Base64.Decoder decoder = Base64.getDecoder();

        /** 存放base64格式的图片String */
        List<String> bufferedImages = new ArrayList<>();

        /** 人体识别 */
        MyFaceApi.myHumanBodySegment(imgPath, bufferedImages);

        /** 等待人体识别完成 */
        while (bufferedImages.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                log.error("sleep error, exception={}", e);
            }
        }

        /** 保存图片 */
        try {
            OutputStream os = new FileOutputStream(imgPath);
            os.write(decoder.decode(bufferedImages.get(0)), 0, decoder.decode(bufferedImages.get(0)).length);
            os.flush();
            os.close();
        } catch (IOException e) {
            log.error("IO error, exception={}, img={}", e, imgPath);
        }
    }

    /**
     * 转换图片格式 png转换为jpg
     *
     * @param imgPath 图片地址
     */
    private void formatConversion(String imgPath) {
        try {
            /** 1.读取图片 */
            BufferedImage bufferedImage = ImageIO.read(new File(imgPath));
            /** 2.创建一个空白大小相同的RGB背景 */
            BufferedImage newBufferedImage = new BufferedImage(bufferedImage.getWidth(), bufferedImage.getHeight(),
                BufferedImage.TYPE_INT_RGB);
            newBufferedImage.createGraphics().drawImage(bufferedImage, 0, 0, Color.WHITE, null);
            /** 3.再次写入的时候以jpeg图片格式 */
            ImageIO.write(newBufferedImage, "jpg", new File(imgPath));
        } catch (IOException e) {
            log.error("IO error, exception={}, img={}", e, imgPath);
        }
    }

    /**
     * 把图片中的各个人分离出来<br>
     * 图片保存格式如： img/1.jpg--img/1_0.jpg img/2_1.jpg...
     *
     * @param imgPath 图片所在文件夹
     * @return 图片中有多少人
     */
    private int cutPicture(String imgPath) throws FileNotFoundException {

        /** 存放人体位置 */
        List<BodyRectangle> bodyRectangles = new ArrayList<>();

        /** 人体识别 */
        MyFaceApi.myHumanBodySkeleton(imgPath, bodyRectangles);

        /** 等待人体识别完成 */
        while (bodyRectangles.isEmpty()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
	    String path = ResourceUtils.getURL("classpath:static/").getPath();
        try {
            for (int i = 0; i < bodyRectangles.size(); ++i) {
                /** 保存图片 */
                PicCut.cut(bodyRectangles.get(i).getLeft(), bodyRectangles.get(i).getTop(),
                    bodyRectangles.get(i).getWidth(), bodyRectangles.get(i).getHeight(), imgPath,
		                path + i + ".jpg");
            }
        } catch (IOException e) {
            log.error("IO error, exception={}, img={}", e, imgPath);
        }

        return bodyRectangles.size();
    }

    @Override
    public boolean checkKnowledge(String knowledge, String image) throws IOException {
        // 参数检验
        if (StringUtils.isBlank(knowledge)) {
            log.error("check knowledge fail, parameter invalid, knowledge={}", knowledge);
            return false;
        }
        // TODO 使用摄像头拍摄到的截图

        // OCR检测图片中的文字
        List<String> list = OcrBaiduApi.baiDuOcr(image);
        // 判断图片中是否存在知识点
        return knowledgeService.checkKnowledge(knowledge, list.toString());
    }

}
