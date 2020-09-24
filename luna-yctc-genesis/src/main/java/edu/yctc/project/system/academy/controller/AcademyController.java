package edu.yctc.project.system.academy.controller;

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
import edu.yctc.project.system.academy.domain.Academy;
import edu.yctc.project.system.academy.service.IAcademyService;

/**
 * 学院Controller
 * 
 * @author luna
 * @date 2020-04-15
 */
@Controller
@RequestMapping("/system/academy")
public class AcademyController extends BaseController
{
    private String prefix = "system/academy";

    @Autowired
    private IAcademyService academyService;

    @RequiresPermissions("system:academy:view")
    @GetMapping()
    public String academy()
    {
        return prefix + "/academy";
    }

    /**
     * 查询学院列表
     */
    @RequiresPermissions("system:academy:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Academy academy)
    {
        startPage();
        List<Academy> list = academyService.selectAcademyList(academy);
        return getDataTable(list);
    }

    /**
     * 导出学院列表
     */
    @RequiresPermissions("system:academy:export")
    @Log(title = "学院", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Academy academy)
    {
        List<Academy> list = academyService.selectAcademyList(academy);
        ExcelUtil<Academy> util = new ExcelUtil<Academy>(Academy.class);
        return util.exportExcel(list, "academy");
    }

    /**
     * 新增学院
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学院
     */
    @RequiresPermissions("system:academy:add")
    @Log(title = "学院", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Academy academy)
    {
        return toAjax(academyService.insertAcademy(academy));
    }

    /**
     * 修改学院
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Academy academy = academyService.selectAcademyById(id);
        mmap.put("academy", academy);
        return prefix + "/edit";
    }

    /**
     * 修改保存学院
     */
    @RequiresPermissions("system:academy:edit")
    @Log(title = "学院", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Academy academy)
    {
        return toAjax(academyService.updateAcademy(academy));
    }

    /**
     * 删除学院
     */
    @RequiresPermissions("system:academy:remove")
    @Log(title = "学院", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(academyService.deleteAcademyByIds(ids));
    }
}
