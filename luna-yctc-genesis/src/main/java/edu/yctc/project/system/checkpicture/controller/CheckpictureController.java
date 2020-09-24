package edu.yctc.project.system.checkpicture.controller;

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
import edu.yctc.project.system.checkpicture.domain.Checkpicture;
import edu.yctc.project.system.checkpicture.service.ICheckpictureService;

/**
 * 检测图片Controller
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/system/checkpicture")
public class CheckpictureController extends BaseController
{
    private String prefix = "system/checkpicture";

    @Autowired
    private ICheckpictureService checkpictureService;

    @RequiresPermissions("system:checkpicture:view")
    @GetMapping()
    public String checkpicture()
    {
        return prefix + "/checkpicture";
    }

    /**
     * 查询检测图片列表
     */
    @RequiresPermissions("system:checkpicture:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Checkpicture checkpicture)
    {
        startPage();
        List<Checkpicture> list = checkpictureService.selectCheckpictureList(checkpicture);
        return getDataTable(list);
    }

    /**
     * 导出检测图片列表
     */
    @RequiresPermissions("system:checkpicture:export")
    @Log(title = "检测图片", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Checkpicture checkpicture)
    {
        List<Checkpicture> list = checkpictureService.selectCheckpictureList(checkpicture);
        ExcelUtil<Checkpicture> util = new ExcelUtil<Checkpicture>(Checkpicture.class);
        return util.exportExcel(list, "checkpicture");
    }

    /**
     * 新增检测图片
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存检测图片
     */
    @RequiresPermissions("system:checkpicture:add")
    @Log(title = "检测图片", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Checkpicture checkpicture)
    {
        return toAjax(checkpictureService.insertCheckpicture(checkpicture));
    }

    /**
     * 修改检测图片
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        Checkpicture checkpicture = checkpictureService.selectCheckpictureById(id);
        mmap.put("checkpicture", checkpicture);
        return prefix + "/edit";
    }

    /**
     * 修改保存检测图片
     */
    @RequiresPermissions("system:checkpicture:edit")
    @Log(title = "检测图片", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Checkpicture checkpicture)
    {
        return toAjax(checkpictureService.updateCheckpicture(checkpicture));
    }

    /**
     * 删除检测图片
     */
    @RequiresPermissions("system:checkpicture:remove")
    @Log(title = "检测图片", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(checkpictureService.deleteCheckpictureByIds(ids));
    }
}
