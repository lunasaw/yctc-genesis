package edu.yctc.project.system.state.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.service.IClassroomStateService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 教室占用情况Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/state")
public class ClassroomStateController extends BaseController
{
    private String prefix = "system/state";

    @Autowired
    private IClassroomStateService classroomStateService;

    @RequiresPermissions("system:state:view")
    @GetMapping()
    public String state()
    {
        return prefix + "/state";
    }

    /**
     * 查询教室占用情况列表
     */
    @RequiresPermissions("system:state:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClassroomState classroomState)
    {
        startPage();
        List<ClassroomState> list = classroomStateService.selectClassroomStateList(classroomState);
        return getDataTable(list);
    }

    /**
     * 导出教室占用情况列表
     */
    @RequiresPermissions("system:state:export")
    @Log(title = "教室占用情况", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClassroomState classroomState)
    {
        List<ClassroomState> list = classroomStateService.selectClassroomStateList(classroomState);
        ExcelUtil<ClassroomState> util = new ExcelUtil<ClassroomState>(ClassroomState.class);
        return util.exportExcel(list, "state");
    }

    /**
     * 新增教室占用情况
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存教室占用情况
     */
    @RequiresPermissions("system:state:add")
    @Log(title = "教室占用情况", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClassroomState classroomState)
    {
        return toAjax(classroomStateService.insertClassroomState(classroomState));
    }

    /**
     * 修改教室占用情况
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ClassroomState classroomState = classroomStateService.selectClassroomStateById(id);
        mmap.put("classroomState", classroomState);
        return prefix + "/edit";
    }

    /**
     * 修改保存教室占用情况
     */
    @RequiresPermissions("system:state:edit")
    @Log(title = "教室占用情况", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClassroomState classroomState)
    {
        return toAjax(classroomStateService.updateClassroomState(classroomState));
    }

    /**
     * 删除教室占用情况
     */
    @RequiresPermissions("system:state:remove")
    @Log(title = "教室占用情况", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classroomStateService.deleteClassroomStateByIds(ids));
    }
}
