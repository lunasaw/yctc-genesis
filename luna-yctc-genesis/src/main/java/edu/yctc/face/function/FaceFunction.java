package edu.yctc.face.function;

import java.io.IOException;

import edu.yctc.project.system.infost.domain.Infost;

/**
 * 人脸识别相关功能接口
 * 
 * @author xiaotao
 */
public interface FaceFunction {

    /**
     * 功能：填写数据库tb_user表的face_token<br>
     * 1）通过UserDO得到picture<br>
     * 2）识别picture（通过face++）得到face_token<br>
     * 3）把face_token保存到tb_user表的face_token<br>
     * 4）把face_token保存到face++平台的faceset
     *
     * @param infost 档案
     */
    public void alterFaceToken(Infost infost);

    /**
     * 功能：处理视频 标记出视频中的人的脸部位置 重新生成视频给前端显示<br>
     * 1）把一段视频截成图片<br>
     * 2）处理其中的若干图片标记出视频中的人的脸部位置<br>
     * 3）把图片合成视频<br>
     * 注：解析视频开始时间和解析视频结束时间要和生成的视频时长对应
     *
     * @param videoPath 视频文件地址
     * @param savePath 处理好的视频保存地址
     * @param startTime 解析视频开始时间（默认00:00）
     * @param endTime 解析视频结束时间（默认00:10）
     * @param totalTime 生成的视频时长（默认10秒）
     * @param frame 生成的视频的帧率（默认10帧/秒）
     * @param resolutionRatio 生成的视频的分辨率(默认为1280*720)
     */
    public void dealMedia(String videoPath, String savePath, String startTime, String endTime, String totalTime,
        String frame, String resolutionRatio);

    /**
     * 控制摄像头的活动
     *
     * @param command 控制摄像头的命令
     */
    public void cameraControl(String command);

    /**
     * 识别图片中的文字
     *
     * @param imgPath 图片路径(jpg格式)
     * @return
     */
    public String ocrControl(String imgPath);

    /**
     * 功能：考勤 判断学生签到情况 把学生的签到情况记录到数据库sys_attendance表<br>
     * 1）课程id--所有学生<br>
     * 2）课程id-- 教室DO--equipmentId--视频地址<br>
     * 3）从视频中抽取几张图片进行人脸识别<br>
     * 4）把 签到的 且在先前得到的所有学生数组里面的学生 的考勤信息更新 即更新数据库sys_attendance表的attendState
     *
     * @param lessonId 课程id
     */
    public void checkByLessonId(Long lessonId, String image) throws IOException;

    /**
     * 功能：学生检测状态 判断学生的上课状态 把学生的状态记录到数据库中的sys_student_state表的state<br>
     * 1）课程id--所有学生<br>
     * 2）课程id-- 教室DO--equipmentId--视频地址<br>
     * 3）从视频中抽取几张图片进行抠图（把图片中的背景去掉 把图片中的人单独截图出来）<br>
     * 4）把截出来的图片用python模型进行状态检测<br>
     * 5）更新数据库对应学生的对应时间段的听课状态
     *
     * @param lessonId 课程Id
     */
    public void checkStatusByLessonId(String lessonId) throws IOException;


     /**
     * 功能：学生检测状态 判断学生的上课状态 把学生的状态记录到数据库中的sys_knowledge_student_state表的state<br>
     * 1）课程id--所有学生<br>
     * 2）课程id-- 教室DO--equipmentId--视频地址<br>
     * 3）从视频中抽取几张图片进行抠图（把图片中的背景去掉 把图片中的人单独截图出来）<br>
     * 4）把截出来的图片用python模型进行状态检测<br>
     * 5）更新数据库对应学生的对应时间段的听课状态
     *
     * @param lessonId 课程Id
     * @param knowledge 上课对应的知识点
     */
     public void checkStatusByLessonId(String lessonId, long knowledge) throws IOException;

    /**
     * 功能：检测此时上课所放的PPT是否存在该知识点 <br>
     * 1）得到视频拍摄PPT图片 <br>
     * 2）OCR检测图片中的文字 <br>
     * 3）检测图片中是否包含知识点 <br>
     * 4）如果包含知识点，返回true <br>
     * 5）如果不包含知识点，返回false <br>
     *
     * @param knowledge
     * @return
     */
    public boolean checkKnowledge(String knowledge, String image) throws IOException;

}
