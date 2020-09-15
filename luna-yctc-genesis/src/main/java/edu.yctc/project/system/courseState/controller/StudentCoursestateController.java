package edu.yctc.project.system.courseState.controller;

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
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.service.IStudentCoursestateService;

/**
 * 学生上课状态信息汇总Controller
 * 
 * @author yctc
 * @date 2020-04-17
 */
@Controller
@RequestMapping("/system/courseState")
public class StudentCoursestateController extends BaseController
{
    private String prefix = "system/courseState";

    @Autowired
    private IStudentCoursestateService studentCoursestateService;

    @RequiresPermissions("system:courseState:view")
    @GetMapping()
    public String courseState()
    {
        return prefix + "/courseState";
    }

    /**
     * 查询学生上课状态信息汇总列表
     */
    @RequiresPermissions("system:courseState:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(StudentCoursestate studentCoursestate)
    {
        startPage();
        List<StudentCoursestate> list = studentCoursestateService.selectStudentCoursestateList(studentCoursestate);
        return getDataTable(list);
    }

    /**
     * 导出学生上课状态信息汇总列表
     */
    @RequiresPermissions("system:courseState:export")
    @Log(title = "学生上课状态信息汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(StudentCoursestate studentCoursestate)
    {
        List<StudentCoursestate> list = studentCoursestateService.selectStudentCoursestateList(studentCoursestate);
        ExcelUtil<StudentCoursestate> util = new ExcelUtil<StudentCoursestate>(StudentCoursestate.class);
        return util.exportExcel(list, "courseState");
    }

    /**
     * 新增学生上课状态信息汇总
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生上课状态信息汇总
     */
    @RequiresPermissions("system:courseState:add")
    @Log(title = "学生上课状态信息汇总", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(StudentCoursestate studentCoursestate)
    {
        return toAjax(studentCoursestateService.insertStudentCoursestate(studentCoursestate));
    }

    /**
     * 修改学生上课状态信息汇总
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        StudentCoursestate studentCoursestate = studentCoursestateService.selectStudentCoursestateById(id);
        mmap.put("studentCoursestate", studentCoursestate);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生上课状态信息汇总
     */
    @RequiresPermissions("system:courseState:edit")
    @Log(title = "学生上课状态信息汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(StudentCoursestate studentCoursestate)
    {
        return toAjax(studentCoursestateService.updateStudentCoursestate(studentCoursestate));
    }

    /**
     * 删除学生上课状态信息汇总
     */
    @RequiresPermissions("system:courseState:remove")
    @Log(title = "学生上课状态信息汇总", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(studentCoursestateService.deleteStudentCoursestateByIds(ids));
    }
}
