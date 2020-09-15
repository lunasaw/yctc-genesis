package edu.yctc.project.system.attendance.controller;

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
import edu.yctc.project.system.attendance.domain.Attendance;
import edu.yctc.project.system.attendance.service.IAttendanceService;

/**
 * 学生考勤点名汇总Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/attendance")
public class AttendanceController extends BaseController
{
    private String prefix = "system/attendance";

    @Autowired
    private IAttendanceService attendanceService;

    @RequiresPermissions("system:attendance:view")
    @GetMapping()
    public String attendance()
    {
        return prefix + "/attendance";
    }

    /**
     * 查询学生考勤点名汇总列表
     */
    @RequiresPermissions("system:attendance:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Attendance attendance)
    {
        startPage();
        List<Attendance> list = attendanceService.selectAttendanceList(attendance);
        return getDataTable(list);
    }

    /**
     * 导出学生考勤点名汇总列表
     */
    @RequiresPermissions("system:attendance:export")
    @Log(title = "学生考勤点名汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Attendance attendance)
    {
        List<Attendance> list = attendanceService.selectAttendanceList(attendance);
        ExcelUtil<Attendance> util = new ExcelUtil<Attendance>(Attendance.class);
        return util.exportExcel(list, "attendance");
    }

    /**
     * 新增学生考勤点名汇总
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生考勤点名汇总
     */
    @RequiresPermissions("system:attendance:add")
    @Log(title = "学生考勤点名汇总", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Attendance attendance)
    {
        return toAjax(attendanceService.insertAttendance(attendance));
    }

    /**
     * 修改学生考勤点名汇总
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Attendance attendance = attendanceService.selectAttendanceById(id);
        mmap.put("attendance", attendance);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生考勤点名汇总
     */
    @RequiresPermissions("system:attendance:edit")
    @Log(title = "学生考勤点名汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Attendance attendance)
    {
        return toAjax(attendanceService.updateAttendance(attendance));
    }

    /**
     * 删除学生考勤点名汇总
     */
    @RequiresPermissions("system:attendance:remove")
    @Log(title = "学生考勤点名汇总", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(attendanceService.deleteAttendanceByIds(ids));
    }

}
