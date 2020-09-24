package edu.yctc.project.system.knowledgeStudentState.controller;

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
import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;
import edu.yctc.project.system.knowledgeStudentState.service.IKnowledgeStudentStateService;

/**
 * 知识点和学生状态关联Controller
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/system/knowledgeStudentState")
public class KnowledgeStudentStateController extends BaseController
{
    private String prefix = "system/knowledgeStudentState";

    @Autowired
    private IKnowledgeStudentStateService knowledgeStudentStateService;

    @RequiresPermissions("system:knowledgeStudentState:view")
    @GetMapping()
    public String knowledgeStudentState()
    {
        return prefix + "/knowledgeStudentState";
    }

    /**
     * 查询知识点和学生状态关联列表
     */
    @RequiresPermissions("system:knowledgeStudentState:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KnowledgeStudentState knowledgeStudentState)
    {


        startPage();
        List<KnowledgeStudentState> list = knowledgeStudentStateService.selectKnowledgeStudentStateList(knowledgeStudentState);
        return getDataTable(list);
    }

    /**
     * 导出知识点和学生状态关联列表
     */
    @RequiresPermissions("system:knowledgeStudentState:export")
    @Log(title = "知识点和学生状态关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KnowledgeStudentState knowledgeStudentState)
    {
        List<KnowledgeStudentState> list = knowledgeStudentStateService.selectKnowledgeStudentStateList(knowledgeStudentState);
        ExcelUtil<KnowledgeStudentState> util = new ExcelUtil<KnowledgeStudentState>(KnowledgeStudentState.class);
        return util.exportExcel(list, "knowledgeStudentState");
    }

    /**
     * 新增知识点和学生状态关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存知识点和学生状态关联
     */
    @RequiresPermissions("system:knowledgeStudentState:add")
    @Log(title = "知识点和学生状态关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KnowledgeStudentState knowledgeStudentState)
    {
        return toAjax(knowledgeStudentStateService.insertKnowledgeStudentState(knowledgeStudentState));
    }

    /**
     * 修改知识点和学生状态关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KnowledgeStudentState knowledgeStudentState = knowledgeStudentStateService.selectKnowledgeStudentStateById(id);
        mmap.put("knowledgeStudentState", knowledgeStudentState);
        return prefix + "/edit";
    }

    /**
     * 修改保存知识点和学生状态关联
     */
    @RequiresPermissions("system:knowledgeStudentState:edit")
    @Log(title = "知识点和学生状态关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KnowledgeStudentState knowledgeStudentState)
    {
        return toAjax(knowledgeStudentStateService.updateKnowledgeStudentState(knowledgeStudentState));
    }

    /**
     * 删除知识点和学生状态关联
     */
    @RequiresPermissions("system:knowledgeStudentState:remove")
    @Log(title = "知识点和学生状态关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(knowledgeStudentStateService.deleteKnowledgeStudentStateByIds(ids));
    }
}
