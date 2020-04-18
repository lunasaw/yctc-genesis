package edu.yctc.project.system.tea.controller;

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
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 老师授课信息Controller
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/system/tea")
public class LessonTeaController extends BaseController
{
    private String prefix = "system/tea";

    @Autowired
    private ILessonTeaService lessonTeaService;

    @RequiresPermissions("system:tea:view")
    @GetMapping()
    public String tea()
    {
        return prefix + "/tea";
    }

    /**
     * 查询老师授课信息列表
     */
    @RequiresPermissions("system:tea:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LessonTea lessonTea)
    {
        startPage();
        List<LessonTea> list = lessonTeaService.selectLessonTeaList(lessonTea);
        return getDataTable(list);
    }

    /**
     * 导出老师授课信息列表
     */
    @RequiresPermissions("system:tea:export")
    @Log(title = "老师授课信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LessonTea lessonTea)
    {
        List<LessonTea> list = lessonTeaService.selectLessonTeaList(lessonTea);
        ExcelUtil<LessonTea> util = new ExcelUtil<LessonTea>(LessonTea.class);
        return util.exportExcel(list, "tea");
    }

    /**
     * 新增老师授课信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存老师授课信息
     */
    @RequiresPermissions("system:tea:add")
    @Log(title = "老师授课信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LessonTea lessonTea)
    {
        return toAjax(lessonTeaService.insertLessonTea(lessonTea));
    }

    /**
     * 修改老师授课信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LessonTea lessonTea = lessonTeaService.selectLessonTeaById(id);
        mmap.put("lessonTea", lessonTea);
        return prefix + "/edit";
    }

    /**
     * 修改保存老师授课信息
     */
    @RequiresPermissions("system:tea:edit")
    @Log(title = "老师授课信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LessonTea lessonTea)
    {
        return toAjax(lessonTeaService.updateLessonTea(lessonTea));
    }

    /**
     * 删除老师授课信息
     */
    @RequiresPermissions("system:tea:remove")
    @Log(title = "老师授课信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lessonTeaService.deleteLessonTeaByIds(ids));
    }
}
