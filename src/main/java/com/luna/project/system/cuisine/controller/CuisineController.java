package com.luna.project.system.cuisine.controller;

import java.util.List;

import com.luna.project.system.cuisine.domain.Cuisine;
import com.luna.project.system.cuisine.service.ICuisineService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.luna.framework.aspectj.lang.annotation.Log;
import com.luna.framework.aspectj.lang.enums.BusinessType;

import com.luna.framework.web.controller.BaseController;
import com.luna.framework.web.domain.AjaxResult;
import com.luna.common.utils.poi.ExcelUtil;
import com.luna.framework.web.page.TableDataInfo;

/**
 * 菜品信息Controller
 * 
 * @author luna
 * @date 2020-03-28
 */
@Controller
@RequestMapping("/system/cuisine")
public class CuisineController extends BaseController
{
    private String prefix = "system/cuisine";

    @Autowired
    private ICuisineService cuisineService;

    @RequiresPermissions("system:cuisine:view")
    @GetMapping()
    public String cuisine()
    {
        return prefix + "/cuisine";
    }

    /**
     * 查询菜品信息列表
     */
    @RequiresPermissions("system:cuisine:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Cuisine cuisine)
    {
        startPage();
        List<Cuisine> list = cuisineService.selectCuisineList(cuisine);
        return getDataTable(list);
    }

    /**
     * 导出菜品信息列表
     */
    @RequiresPermissions("system:cuisine:export")
    @Log(title = "菜品信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Cuisine cuisine)
    {
        List<Cuisine> list = cuisineService.selectCuisineList(cuisine);
        ExcelUtil<Cuisine> util = new ExcelUtil<Cuisine>(Cuisine.class);
        return util.exportExcel(list, "cuisine");
    }

    /**
     * 新增菜品信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存菜品信息
     */
    @RequiresPermissions("system:cuisine:add")
    @Log(title = "菜品信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Cuisine cuisine)
    {
        return toAjax(cuisineService.insertCuisine(cuisine));
    }

    /**
     * 修改菜品信息
     */
    @GetMapping("/edit/{cuisineId}")
    public String edit(@PathVariable("cuisineId") Long cuisineId, ModelMap mmap)
    {
        Cuisine cuisine = cuisineService.selectCuisineById(cuisineId);
        mmap.put("cuisine", cuisine);
        return prefix + "/edit";
    }

    /**
     * 修改保存菜品信息
     */
    @RequiresPermissions("system:cuisine:edit")
    @Log(title = "菜品信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Cuisine cuisine)
    {
        return toAjax(cuisineService.updateCuisine(cuisine));
    }

    /**
     * 删除菜品信息
     */
    @RequiresPermissions("system:cuisine:remove")
    @Log(title = "菜品信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(cuisineService.deleteCuisineByIds(ids));
    }
}
