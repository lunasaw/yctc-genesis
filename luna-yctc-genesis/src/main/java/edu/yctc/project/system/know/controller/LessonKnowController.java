package edu.yctc.project.system.know.controller;

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
import edu.yctc.project.system.know.domain.LessonKnow;
import edu.yctc.project.system.know.service.ILessonKnowService;

/**
 * 课时知识点信息Controller
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/system/know")
public class LessonKnowController extends BaseController
{
    private String prefix = "system/know";

    @Autowired
    private ILessonKnowService lessonKnowService;

    @RequiresPermissions("system:know:view")
    @GetMapping()
    public String know()
    {
        return prefix + "/know";
    }

    /**
     * 查询课时知识点信息列表
     */
    @RequiresPermissions("system:know:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(LessonKnow lessonKnow)
    {
        startPage();
        List<LessonKnow> list = lessonKnowService.selectLessonKnowList(lessonKnow);
        return getDataTable(list);
    }

    /**
     * 导出课时知识点信息列表
     */
    @RequiresPermissions("system:know:export")
    @Log(title = "课时知识点信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(LessonKnow lessonKnow)
    {
        List<LessonKnow> list = lessonKnowService.selectLessonKnowList(lessonKnow);
        ExcelUtil<LessonKnow> util = new ExcelUtil<LessonKnow>(LessonKnow.class);
        return util.exportExcel(list, "know");
    }

    /**
     * 新增课时知识点信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存课时知识点信息
     */
    @RequiresPermissions("system:know:add")
    @Log(title = "课时知识点信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(LessonKnow lessonKnow)
    {
        return toAjax(lessonKnowService.insertLessonKnow(lessonKnow));
    }

    /**
     * 修改课时知识点信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        LessonKnow lessonKnow = lessonKnowService.selectLessonKnowById(id);
        mmap.put("lessonKnow", lessonKnow);
        return prefix + "/edit";
    }

    /**
     * 修改保存课时知识点信息
     */
    @RequiresPermissions("system:know:edit")
    @Log(title = "课时知识点信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(LessonKnow lessonKnow)
    {
        return toAjax(lessonKnowService.updateLessonKnow(lessonKnow));
    }

    /**
     * 删除课时知识点信息
     */
    @RequiresPermissions("system:know:remove")
    @Log(title = "课时知识点信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(lessonKnowService.deleteLessonKnowByIds(ids));
    }
}
