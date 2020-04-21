package edu.yctc.project.system.applyClassroom.controller;

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
import edu.yctc.project.system.applyClassroom.domain.UserApplyclassroom;
import edu.yctc.project.system.applyClassroom.service.IUserApplyclassroomService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 教室申请Controller
 * 
 * @author yctc
 * @date 2020-04-21
 */
@Controller
@RequestMapping("/system/applyClassroom")
public class UserApplyclassroomController extends BaseController
{
    private String prefix = "system/applyClassroom";

    @Autowired
    private IUserApplyclassroomService userApplyclassroomService;

    @RequiresPermissions("system:applyClassroom:view")
    @GetMapping()
    public String applyClassroom()
    {
        return prefix + "/applyClassroom";
    }

    /**
     * 查询教室申请列表
     */
    @RequiresPermissions("system:applyClassroom:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(UserApplyclassroom userApplyclassroom)
    {
        startPage();
        List<UserApplyclassroom> list = userApplyclassroomService.selectUserApplyclassroomList(userApplyclassroom);
        return getDataTable(list);
    }

    /**
     * 导出教室申请列表
     */
    @RequiresPermissions("system:applyClassroom:export")
    @Log(title = "教室申请", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(UserApplyclassroom userApplyclassroom)
    {
        List<UserApplyclassroom> list = userApplyclassroomService.selectUserApplyclassroomList(userApplyclassroom);
        ExcelUtil<UserApplyclassroom> util = new ExcelUtil<UserApplyclassroom>(UserApplyclassroom.class);
        return util.exportExcel(list, "applyClassroom");
    }

    /**
     * 新增教室申请
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存教室申请
     */
    @RequiresPermissions("system:applyClassroom:add")
    @Log(title = "教室申请", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(UserApplyclassroom userApplyclassroom)
    {
        return toAjax(userApplyclassroomService.insertUserApplyclassroom(userApplyclassroom));
    }

    /**
     * 修改教室申请
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        UserApplyclassroom userApplyclassroom = userApplyclassroomService.selectUserApplyclassroomById(id);
        mmap.put("userApplyclassroom", userApplyclassroom);
        return prefix + "/edit";
    }

    /**
     * 修改保存教室申请
     */
    @RequiresPermissions("system:applyClassroom:edit")
    @Log(title = "教室申请", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(UserApplyclassroom userApplyclassroom)
    {
        return toAjax(userApplyclassroomService.updateUserApplyclassroom(userApplyclassroom));
    }

    /**
     * 删除教室申请
     */
    @RequiresPermissions("system:applyClassroom:remove")
    @Log(title = "教室申请", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(userApplyclassroomService.deleteUserApplyclassroomByIds(ids));
    }
}
