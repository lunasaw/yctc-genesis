package edu.yctc.project.system.classroom.controller;

import java.util.List;

import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.classroom.service.IClassroomService;

/**
 * 教室Controller
 * 
 * @author luna
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/system/classroom")
public class ClassroomController extends BaseController
{
    private String prefix = "system/classroom";

    @Autowired
    private IClassroomService classroomService;

    @RequiresPermissions("system:classroom:view")
    @GetMapping()
    public String classroom()
    {
        return prefix + "/classroom";
    }

    /**
     * 查询教室列表
     */
    @RequiresPermissions("system:classroom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Classroom classroom)
    {
        startPage();
        List<Classroom> list = classroomService.selectClassroomList(classroom);
        return getDataTable(list);
    }

    /**
     * 导出教室列表
     */
    @RequiresPermissions("system:classroom:export")
    @Log(title = "教室", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Classroom classroom)
    {
        List<Classroom> list = classroomService.selectClassroomList(classroom);
        ExcelUtil<Classroom> util = new ExcelUtil<Classroom>(Classroom.class);
        return util.exportExcel(list, "classroom");
    }

    /**
     * 新增教室
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存教室
     */
    @RequiresPermissions("system:classroom:add")
    @Log(title = "教室", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Classroom classroom)
    {
        return toAjax(classroomService.insertClassroom(classroom));
    }

    /**
     * 修改教室
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Classroom classroom = classroomService.selectClassroomById(id);
        mmap.put("classroom", classroom);
        return prefix + "/edit";
    }

    /**
     * 修改保存教室
     */
    @RequiresPermissions("system:classroom:edit")
    @Log(title = "教室", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Classroom classroom)
    {
        return toAjax(classroomService.updateClassroom(classroom));
    }

    /**
     * 删除教室
     */
    @RequiresPermissions("system:classroom:remove")
    @Log(title = "教室", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classroomService.deleteClassroomByIds(ids));
    }
}
