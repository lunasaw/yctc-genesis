package edu.yctc.project.system.lesson.controller;

import java.util.List;

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
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.service.ILessonService;

/**
 * 课时信息Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/lesson")
public class LessonController extends BaseController
{
    private String prefix = "system/lesson";

    @Autowired
    private ILessonService lessonService;

    @RequiresPermissions("system:lesson:view")
    @GetMapping()
    public String lesson()
    {
        return prefix + "/lesson";
    }

    /**
     * 查询课时信息列表
     */
    @RequiresPermissions("system:lesson:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Lesson lesson)
    {
        startPage();
        List<Lesson> list = lessonService.selectLessonList(lesson);
        return getDataTable(list);
    }

    /**
     * 导出课时信息列表
     */
    @RequiresPermissions("system:lesson:export")
    @Log(title = "课时信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Lesson lesson)
    {
        List<Lesson> list = lessonService.selectLessonList(lesson);
        ExcelUtil<Lesson> util = new ExcelUtil<Lesson>(Lesson.class);
        return util.exportExcel(list, "lesson");
    }

    /**
     * 新增课时信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课时信息
     */
    @RequiresPermissions("system:lesson:add")
    @Log(title = "课时信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Lesson lesson)
    {
        return toAjax(lessonService.insertLesson(lesson));
    }

    /**
     * 修改课时信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Lesson lesson = lessonService.selectLessonById(id);
        mmap.put("lesson", lesson);
        return prefix + "/edit";
    }

    /**
     * 修改保存课时信息
     */
    @RequiresPermissions("system:lesson:edit")
    @Log(title = "课时信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Lesson lesson)
    {
        return toAjax(lessonService.updateLesson(lesson));
    }

    /**
     * 删除课时信息
     */
    @RequiresPermissions("system:lesson:remove")
    @Log(title = "课时信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lessonService.deleteLessonByIds(ids));
    }
}
