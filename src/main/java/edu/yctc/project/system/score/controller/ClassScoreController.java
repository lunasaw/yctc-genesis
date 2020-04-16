package edu.yctc.project.system.score.controller;

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
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.service.IClassScoreService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 学生上课评分汇总Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/score")
public class ClassScoreController extends BaseController
{
    private String prefix = "system/score";

    @Autowired
    private IClassScoreService classScoreService;

    @RequiresPermissions("system:score:view")
    @GetMapping()
    public String score()
    {
        return prefix + "/score";
    }

    /**
     * 查询学生上课评分汇总列表
     */
    @RequiresPermissions("system:score:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ClassScore classScore)
    {
        startPage();
        List<ClassScore> list = classScoreService.selectClassScoreList(classScore);
        return getDataTable(list);
    }

    /**
     * 导出学生上课评分汇总列表
     */
    @RequiresPermissions("system:score:export")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ClassScore classScore)
    {
        List<ClassScore> list = classScoreService.selectClassScoreList(classScore);
        ExcelUtil<ClassScore> util = new ExcelUtil<ClassScore>(ClassScore.class);
        return util.exportExcel(list, "score");
    }

    /**
     * 新增学生上课评分汇总
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生上课评分汇总
     */
    @RequiresPermissions("system:score:add")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ClassScore classScore)
    {
        return toAjax(classScoreService.insertClassScore(classScore));
    }

    /**
     * 修改学生上课评分汇总
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ClassScore classScore = classScoreService.selectClassScoreById(id);
        mmap.put("classScore", classScore);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生上课评分汇总
     */
    @RequiresPermissions("system:score:edit")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ClassScore classScore)
    {
        return toAjax(classScoreService.updateClassScore(classScore));
    }

    /**
     * 删除学生上课评分汇总
     */
    @RequiresPermissions("system:score:remove")
    @Log(title = "学生上课评分汇总", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(classScoreService.deleteClassScoreByIds(ids));
    }
}
