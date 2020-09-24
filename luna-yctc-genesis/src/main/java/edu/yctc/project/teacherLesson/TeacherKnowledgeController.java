package edu.yctc.project.teacherLesson;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.classes.domain.Classes;
import edu.yctc.project.system.classes.service.IClassesService;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.course.service.ICourseService;
import edu.yctc.project.system.infost.service.IInfostService;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.service.ILessonService;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;
import edu.yctc.project.system.user.domain.User;
import edu.yctc.project.system.user.service.IUserService;
import edu.yctc.project.vo.KnowledgeVO;

/**
 * 知识点概览Controller
 * 
 * @author yctc
 * @date 2020-04-21
 */
@Controller
@RequestMapping("/system/teacherKnowledge")
public class TeacherKnowledgeController extends BaseController {
    private String            prefix = "system/teacherKnowledge";

    @Autowired
    private IKnowledgeService knowledgeService;

    @Autowired
    private ILessonTeaService lessonTeaService;

    @Autowired
    private ICourseService    courseService;

    @Autowired
    private IUserService      userService;

    @Autowired
    private IInfostService    infostService;

    @Autowired
    private IClassesService   classesService;

    @Autowired
    private ILessonService    lessonService;

    @RequiresPermissions("system:teacherKnowledge:view")
    @GetMapping()
    public String knowledge() {
        return prefix + "/knowledge";
    }

    /**
     * 查询知识点概览列表
     */
    @RequiresPermissions("system:teacherKnowledge:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Knowledge knowledge) {
        Long userId = ShiroUtils.getUserId();
        LessonTea lessonTea = new LessonTea();
        lessonTea.setUserId(userId);
        List<LessonTea> lessonTeas = lessonTeaService.selectLessonTeaList(lessonTea);
        if (lessonTeas.size() == 0) {
            return getDataTable(new ArrayList<ClassScore>());
        }
        List<Knowledge> list = new ArrayList<>();
        for (int i = 0; i < lessonTeas.size(); i++) {
            knowledge.setLessonId(lessonTeas.get(i).getLessonId());
            List<Knowledge> list1 = knowledgeService.selectKnowledgeList(knowledge);
            list.addAll(list1);
        }
        return getDataTable(list);
    }

    /**
     * 导出知识点概览列表
     */
    @RequiresPermissions("system:teacherKnowledge:export")
    @Log(title = "知识点概览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Knowledge knowledge) {
        List<Knowledge> list = knowledgeService.selectKnowledgeList(knowledge);
        ExcelUtil<Knowledge> util = new ExcelUtil<Knowledge>(Knowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增知识点概览
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存知识点概览
     */
    @RequiresPermissions("system:teacherKnowledge:add")
    @Log(title = "知识点概览", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KnowledgeVO knowledgeVO) {
        Long userId = ShiroUtils.getUserId();
        User user = userService.selectUserById(userId);
        Knowledge knowledge1 = new Knowledge();
        knowledge1.setContent(knowledgeVO.getContent());
        knowledge1.setLength(knowledgeVO.getLength());
        // 查找课程
        Course course = new Course();
        course.setName(knowledgeVO.getName());
        course.setYear(knowledgeVO.getYear());
        course.setTerm(knowledgeVO.getTerm());
        // 查找班级
        Classes classes = new Classes();
        classes.setDept(user.getDept());
        classes.setNumber(knowledgeVO.getClassNumber());
        List<Classes> classes1 = classesService.selectClassesList(classes);
        // 获取教室编号
        Long id = classes1.get(0).getId();
        course.setClassesId(String.valueOf(id));
        List<Course> courses = courseService.selectCourseList(course);
        Course course1 = courses.get(0);
        // 获取课程
        Lesson lesson = new Lesson();
        lesson.setCourseId(course1.getId());
        lesson.setNumber(knowledgeVO.getNumber());
        List<Lesson> lessons = lessonService.selectLessonList(lesson);
        if (lessons.size() == 0) {
            lessonService.insertLesson(lesson);
            knowledge1.setLessonId(lesson.getId());
        } else {
            knowledge1.setLessonId(lessons.get(0).getId());
        }
        return toAjax(knowledgeService.insertKnowledge(knowledge1));
    }

    /**
     * 修改知识点概览
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        Knowledge knowledge = knowledgeService.selectKnowledgeById(id);
        mmap.put("knowledge", knowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存知识点概览
     */
    @RequiresPermissions("system:teacherKnowledge:edit")
    @Log(title = "知识点概览", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Knowledge knowledge) {
        return toAjax(knowledgeService.updateKnowledge(knowledge));
    }

    /**
     * 删除知识点概览
     */
    @RequiresPermissions("system:teacherKnowledge:remove")
    @Log(title = "知识点概览", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(knowledgeService.deleteKnowledgeByIds(ids));
    }
}
