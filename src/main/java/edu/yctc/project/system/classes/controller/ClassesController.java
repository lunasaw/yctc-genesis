package edu.yctc.project.system.classes.controller;

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
import edu.yctc.project.system.classes.domain.Classes;
import edu.yctc.project.system.classes.service.IClassesService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 班级信息Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/classes")
public class ClassesController extends BaseController
{
    private String prefix = "system/classes";

    @Autowired
    private IClassesService classesService;

    @RequiresPermissions("system:classes:view")
    @GetMapping()
    public String classes()
    {
        return prefix + "/classes";
    }

    /**
     * 查询班级信息列表
     */
    @RequiresPermissions("system:classes:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Classes classes)
    {
        startPage();
        List<Classes> list = classesService.selectClassesList(classes);
        return getDataTable(list);
    }

    /**
     * 导出班级信息列表
     */
    @RequiresPermissions("system:classes:export")
    @Log(title = "班级信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Classes classes)
    {
        List<Classes> list = classesService.selectClassesList(classes);
        ExcelUtil<Classes> util = new ExcelUtil<Classes>(Classes.class);
        return util.exportExcel(list, "classes");
    }

    /**
     * 新增班级信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存班级信息
     */
    @RequiresPermissions("system:classes:add")
    @Log(title = "班级信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Classes classes)
    {
        return toAjax(classesService.insertClasses(classes));
    }

    /**
     * 修改班级信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Classes classes = classesService.selectClassesById(id);
        mmap.put("classes", classes);
        return prefix + "/edit";
    }

    /**
     * 修改保存班级信息
     */
    @RequiresPermissions("system:classes:edit")
    @Log(title = "班级信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Classes classes)
    {
        return toAjax(classesService.updateClasses(classes));
    }

    /**
     * 删除班级信息
     */
    @RequiresPermissions("system:classes:remove")
    @Log(title = "班级信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classesService.deleteClassesByIds(ids));
    }
}
