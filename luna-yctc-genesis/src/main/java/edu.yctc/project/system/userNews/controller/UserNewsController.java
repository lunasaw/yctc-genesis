package edu.yctc.project.system.userNews.controller;

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
import edu.yctc.project.system.userNews.domain.UserNews;
import edu.yctc.project.system.userNews.service.IUserNewsService;

/**
 * 用户和通知关联Controller
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Controller
@RequestMapping("/system/userNews")
public class UserNewsController extends BaseController
{
    private String prefix = "system/userNews";

    @Autowired
    private IUserNewsService userNewsService;

    @RequiresPermissions("system:userNews:view")
    @GetMapping()
    public String userNews()
    {
        return prefix + "/userNews";
    }

    /**
     * 查询用户和通知关联列表
     */
    @RequiresPermissions("system:userNews:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserNews userNews)
    {
        startPage();
        List<UserNews> list = userNewsService.selectUserNewsList(userNews);
        return getDataTable(list);
    }

    /**
     * 导出用户和通知关联列表
     */
    @RequiresPermissions("system:userNews:export")
    @Log(title = "用户和通知关联", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserNews userNews)
    {
        List<UserNews> list = userNewsService.selectUserNewsList(userNews);
        ExcelUtil<UserNews> util = new ExcelUtil<UserNews>(UserNews.class);
        return util.exportExcel(list, "userNews");
    }

    /**
     * 新增用户和通知关联
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存用户和通知关联
     */
    @RequiresPermissions("system:userNews:add")
    @Log(title = "用户和通知关联", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserNews userNews)
    {
        return toAjax(userNewsService.insertUserNews(userNews));
    }

    /**
     * 修改用户和通知关联
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserNews userNews = userNewsService.selectUserNewsById(id);
        mmap.put("userNews", userNews);
        return prefix + "/edit";
    }

    /**
     * 修改保存用户和通知关联
     */
    @RequiresPermissions("system:userNews:edit")
    @Log(title = "用户和通知关联", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserNews userNews)
    {
        return toAjax(userNewsService.updateUserNews(userNews));
    }

    /**
     * 删除用户和通知关联
     */
    @RequiresPermissions("system:userNews:remove")
    @Log(title = "用户和通知关联", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userNewsService.deleteUserNewsByIds(ids));
    }
}
