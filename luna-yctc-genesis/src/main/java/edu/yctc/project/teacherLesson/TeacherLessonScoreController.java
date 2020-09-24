package edu.yctc.project.teacherLesson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.lesson.service.ILessonService;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.service.IClassScoreService;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;

/**
 * @author Luna@win10
 * @date 2020/4/23 12:00
 */
@Controller
@RequestMapping("/system/teacher")
public class TeacherLessonScoreController extends BaseController {

    private String             prefix = "system/lessonScore";

    @Autowired
    private IClassScoreService classScoreService;

    @Autowired
    private ILessonTeaService  lessonTeaService;

    @Resource
    private ILessonService     lessonService;

    @RequiresPermissions("system:teacher:view")
    @GetMapping("lessonScore")
    public String score() {
        return prefix + "/score";
    }

    /**
     * 查询教学评分
     */
    @RequiresPermissions("system:teacher:list")
    @PostMapping("/lessonScorelist")
    @ResponseBody
    public TableDataInfo list(ClassScore classScore) {
        Long userId = ShiroUtils.getUserId();
        LessonTea lessonTea = new LessonTea();
        lessonTea.setUserId(userId);
        List<LessonTea> lessonTeas = lessonTeaService.selectLessonTeaList(lessonTea);
        if (lessonTeas.size() == 0) {
            return getDataTable(new ArrayList<ClassScore>());
        }
        lessonTea.setUserId(userId);
        List<ClassScore> list = new ArrayList<>();
        Double lessonSocoreSum = 0.0;

        List<ClassScore> classScores = new ArrayList<>();
        for (int i = 0; i < lessonTeas.size(); i++) {
            classScore.setLessonId(lessonTeas.get(i).getLessonId());
            classScores = classScoreService.selectClassScoreList(classScore);
            for (int i1 = 0; i1 < classScores.size(); i1++) {
                Double score = classScores.get(i1).getScore();
                lessonSocoreSum = lessonSocoreSum + score;
            }
            lessonSocoreSum = lessonSocoreSum / classScores.size();
            ClassScore classScore1 = new ClassScore();
            classScore1.setScore(lessonSocoreSum);
            classScore1.setUserId(userId);
            classScore1.setLessonId(lessonTeas.get(i).getLessonId());
            list.add(classScore1);
            lessonSocoreSum = 0.0;
        }
        classScore = new ClassScore();
        classScore.setUserId(userId);
        List<ClassScore> classScores1 = classScoreService.selectClassScoreList(classScore);

        Map<Long, ClassScore> map = new HashMap<>();
        for (int i = 0; i < classScores1.size(); i++) {
            map.put(classScores1.get(i).getLessonId(), list.get(i));
        }

        classScore = new ClassScore();
        for (int i = 0; i < list.size(); i++) {
            boolean b = map.containsKey(list.get(i).getLessonId());
            classScore.setUserId(userId);
            classScore.setLessonId(list.get(i).getLessonId());
            classScore.setScore(list.get(i).getScore());
            if (b) {
                classScoreService.updateClassScore(classScore);
            } else {
                classScoreService.insertClassScore(classScore);
            }
        }

        classScore = new ClassScore();
        classScore.setUserId(userId);
        classScore.setLessonId(null);
        List<ClassScore> classScores2 = classScoreService.selectClassScoreList(classScore);
        return getDataTable(classScores2);
    }
}
