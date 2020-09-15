package edu.yctc.project.system.infost.controller;

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
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.service.IInfostService;

/**
 * 学生/老师档案Controller
 * 
 * @author yctc
 * @date 2020-04-17
 */
@Controller
@RequestMapping("/system/infost")
public class InfostController extends BaseController
{
    private String prefix = "system/infost";

    @Autowired
    private IInfostService infostService;

    @RequiresPermissions("system:infost:view")
    @GetMapping()
    public String infost()
    {
        return prefix + "/infost";
    }

    /**
     * 查询学生/老师档案列表
     */
    @RequiresPermissions("system:infost:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Infost infost)
    {
        startPage();
        List<Infost> list = infostService.selectInfostList(infost);
        return getDataTable(list);
    }

    /**
     * 导出学生/老师档案列表
     */
    @RequiresPermissions("system:infost:export")
    @Log(title = "学生/老师档案", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Infost infost)
    {
        List<Infost> list = infostService.selectInfostList(infost);
        ExcelUtil<Infost> util = new ExcelUtil<Infost>(Infost.class);
        return util.exportExcel(list, "infost");
    }

    /**
     * 新增学生/老师档案
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生/老师档案
     */
    @RequiresPermissions("system:infost:add")
    @Log(title = "学生/老师档案", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Infost infost)
    {
        return toAjax(infostService.insertInfost(infost));
    }

    /**
     * 修改学生/老师档案
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Infost infost = infostService.selectInfostById(id);
        mmap.put("infost", infost);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生/老师档案
     */
    @RequiresPermissions("system:infost:edit")
    @Log(title = "学生/老师档案", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Infost infost)
    {
        return toAjax(infostService.updateInfost(infost));
    }

    /**
     * 删除学生/老师档案
     */
    @RequiresPermissions("system:infost:remove")
    @Log(title = "学生/老师档案", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infostService.deleteInfostByIds(ids));
    }
}
