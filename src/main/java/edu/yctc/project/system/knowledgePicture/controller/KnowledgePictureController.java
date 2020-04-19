package edu.yctc.project.system.knowledgePicture.controller;

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
import edu.yctc.project.system.knowledgePicture.domain.KnowledgePicture;
import edu.yctc.project.system.knowledgePicture.service.IKnowledgePictureService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 图片和知识点关联Controller
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/system/knowledgePicture")
public class KnowledgePictureController extends BaseController
{
    private String prefix = "system/knowledgePicture";

    @Autowired
    private IKnowledgePictureService knowledgePictureService;

    @RequiresPermissions("system:knowledgePicture:view")
    @GetMapping()
    public String knowledgePicture()
    {
        return prefix + "/knowledgePicture";
    }

    /**
     * 查询图片和知识点关联列表
     */
    @RequiresPermissions("system:knowledgePicture:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KnowledgePicture knowledgePicture)
    {
        startPage();
        List<KnowledgePicture> list = knowledgePictureService.selectKnowledgePictureList(knowledgePicture);
        return getDataTable(list);
    }

    /**
     * 导出图片和知识点关联列表
     */
    @RequiresPermissions("system:knowledgePicture:export")
    @Log(title = "图片和知识点关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KnowledgePicture knowledgePicture)
    {
        List<KnowledgePicture> list = knowledgePictureService.selectKnowledgePictureList(knowledgePicture);
        ExcelUtil<KnowledgePicture> util = new ExcelUtil<KnowledgePicture>(KnowledgePicture.class);
        return util.exportExcel(list, "knowledgePicture");
    }

    /**
     * 新增图片和知识点关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存图片和知识点关联
     */
    @RequiresPermissions("system:knowledgePicture:add")
    @Log(title = "图片和知识点关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KnowledgePicture knowledgePicture)
    {
        return toAjax(knowledgePictureService.insertKnowledgePicture(knowledgePicture));
    }

    /**
     * 修改图片和知识点关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KnowledgePicture knowledgePicture = knowledgePictureService.selectKnowledgePictureById(id);
        mmap.put("knowledgePicture", knowledgePicture);
        return prefix + "/edit";
    }

    /**
     * 修改保存图片和知识点关联
     */
    @RequiresPermissions("system:knowledgePicture:edit")
    @Log(title = "图片和知识点关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KnowledgePicture knowledgePicture)
    {
        return toAjax(knowledgePictureService.updateKnowledgePicture(knowledgePicture));
    }

    /**
     * 删除图片和知识点关联
     */
    @RequiresPermissions("system:knowledgePicture:remove")
    @Log(title = "图片和知识点关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(knowledgePictureService.deleteKnowledgePictureByIds(ids));
    }
}
