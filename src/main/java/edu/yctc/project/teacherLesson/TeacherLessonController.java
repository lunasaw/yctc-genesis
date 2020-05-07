package edu.yctc.project.teacherLesson;

import java.util.ArrayList;
import java.util.List;

import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.service.IClassScoreService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;

/**
 * 老师授课信息Controller
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/system/teacher")
public class TeacherLessonController extends BaseController {
    private String             prefix      = "system/teacherLesson";

    @Autowired
    private IClassScoreService classScoreService;

    @Autowired
    private ILessonTeaService  lessonTeaService;

    @RequiresPermissions("system:teacher:view")
    @GetMapping()
    public String tea() {
        return prefix + "/tea";
    }

    @RequiresPermissions("system:teacher:view")
    @GetMapping("/map")
    public String map() {
        return prefix + "/map";
    }

    @RequiresPermissions("system:teacher:view")
    @GetMapping("/graph")
    public String graph() {
        return prefix + "/graph";
    }

    /**
     * 查询老师授课信息列表
     */
    @RequiresPermissions("system:teacher:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LessonTea lessonTea) {
        Long userId = ShiroUtils.getUserId();
        lessonTea.setUserId(userId);
        // startPage();
        List<LessonTea> list = lessonTeaService.selectLessonTeaList(lessonTea);
        return getDataTable(list);
    }

    /**
     * 导出老师授课信息列表
     */
    @RequiresPermissions("system:teacher:export")
    @Log(title = "老师授课信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LessonTea lessonTea) {
        List<LessonTea> list = lessonTeaService.selectLessonTeaList(lessonTea);
        ExcelUtil<LessonTea> util = new ExcelUtil<LessonTea>(LessonTea.class);
        return util.exportExcel(list, "tea");
    }

    @RequiresPermissions("system:teacher:view")
    @GetMapping("/score")
    public String score() {
        return prefix + "/score";
    }

    /**
     * 查询学生上课评分
     */
    @RequiresPermissions("system:teacher:list")
    @PostMapping("/scorelist")
    @ResponseBody
    public TableDataInfo list(ClassScore classScore) {
        Long userId = ShiroUtils.getUserId();
        LessonTea lessonTea = new LessonTea();
        lessonTea.setUserId(userId);
        List<LessonTea> lessonTeas = lessonTeaService.selectLessonTeaList(lessonTea);
        if (lessonTeas.size() == 0) {
            return getDataTable(new ArrayList<ClassScore>());
        }
	    List<ClassScore> classScores=null;
	    List<ClassScore> list = new ArrayList<>();
	    for (int i = 0; i < lessonTeas.size(); i++) {
		    classScore.setLessonId(lessonTeas.get(i).getLessonId());
            // startPage();
		    classScores = classScoreService.selectClassScoreList(classScore);
		    list.addAll(classScores);
	    }
        return getDataTable(list);
    }

    /**
     * 导出学生上课评分汇总列表
     */
    @RequiresPermissions("system:teacher:export")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/scoreexport")
    @ResponseBody
    public AjaxResult export(ClassScore classScore) {
        List<ClassScore> list = classScoreService.selectClassScoreList(classScore);
        ExcelUtil<ClassScore> util = new ExcelUtil<ClassScore>(ClassScore.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 新增学生上课评分汇总
     */
    @GetMapping("/scoreadd")
    public String add() {
        return prefix + "/scoreadd";
    }

    /**
     * 新增保存学生上课评分汇总
     */
    @RequiresPermissions("system:teacher:add")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.INSERT)
    @PostMapping("/scoreadd")
    @ResponseBody
    public AjaxResult addSave(ClassScore classScore) {
        return toAjax(classScoreService.insertClassScore(classScore));
    }

    /**
     * 修改学生上课评分汇总
     */
    @GetMapping("/scoreedit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap) {
        ClassScore classScore = classScoreService.selectClassScoreById(id);
        mmap.put("classScore", classScore);
        return prefix + "/scoreedit";
    }

    /**
     * 修改保存学生上课评分汇总
     */
    @RequiresPermissions("system:teacher:edit")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/socreedit")
    @ResponseBody
    public AjaxResult editSave(ClassScore classScore) {
        return toAjax(classScoreService.updateClassScore(classScore));
    }

    /**
     * 删除学生上课评分汇总
     */
    @RequiresPermissions("system:teacher:remove")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.DELETE)
    @PostMapping("/scoreremove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(classScoreService.deleteClassScoreByIds(ids));
    }
}
