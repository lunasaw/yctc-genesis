package edu.yctc.project.system.knowledge.controller;

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
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;

/**
 * 知识点概览Controller
 * 
 * @author yctc
 * @date 2020-04-21
 */
@Controller
@RequestMapping("/system/knowledge")
public class KnowledgeController extends BaseController
{
    private String prefix = "system/knowledge";

    @Autowired
    private IKnowledgeService knowledgeService;

    @RequiresPermissions("system:knowledge:view")
    @GetMapping()
    public String knowledge()
    {
        return prefix + "/knowledge";
    }

    /**
     * 查询知识点概览列表
     */
    @RequiresPermissions("system:knowledge:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Knowledge knowledge)
    {
        startPage();
        List<Knowledge> list = knowledgeService.selectKnowledgeList(knowledge);
        return getDataTable(list);
    }

    /**
     * 导出知识点概览列表
     */
    @RequiresPermissions("system:knowledge:export")
    @Log(title = "知识点概览", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Knowledge knowledge)
    {
        List<Knowledge> list = knowledgeService.selectKnowledgeList(knowledge);
        ExcelUtil<Knowledge> util = new ExcelUtil<Knowledge>(Knowledge.class);
        return util.exportExcel(list, "knowledge");
    }

    /**
     * 新增知识点概览
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存知识点概览
     */
    @RequiresPermissions("system:knowledge:add")
    @Log(title = "知识点概览", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Knowledge knowledge)
    {
        return toAjax(knowledgeService.insertKnowledge(knowledge));
    }

    /**
     * 修改知识点概览
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Knowledge knowledge = knowledgeService.selectKnowledgeById(id);
        mmap.put("knowledge", knowledge);
        return prefix + "/edit";
    }

    /**
     * 修改保存知识点概览
     */
    @RequiresPermissions("system:knowledge:edit")
    @Log(title = "知识点概览", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Knowledge knowledge)
    {
        return toAjax(knowledgeService.updateKnowledge(knowledge));
    }

    /**
     * 删除知识点概览
     */
    @RequiresPermissions("system:knowledge:remove")
    @Log(title = "知识点概览", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(knowledgeService.deleteKnowledgeByIds(ids));
    }
}
