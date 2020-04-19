package edu.yctc.project.system.stu.controller;

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
import edu.yctc.project.system.stu.domain.CourseStu;
import edu.yctc.project.system.stu.service.ICourseStuService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 学生课程信息Controller
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Controller
@RequestMapping("/system/stu")
public class CourseStuController extends BaseController
{
    private String prefix = "system/stu";

    @Autowired
    private ICourseStuService courseStuService;

    @RequiresPermissions("system:stu:view")
    @GetMapping()
    public String stu()
    {
        return prefix + "/stu";
    }

    /**
     * 查询学生课程信息列表
     */
    @RequiresPermissions("system:stu:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CourseStu courseStu)
    {
        startPage();
        List<CourseStu> list = courseStuService.selectCourseStuList(courseStu);
        return getDataTable(list);
    }

    /**
     * 导出学生课程信息列表
     */
    @RequiresPermissions("system:stu:export")
    @Log(title = "学生课程信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CourseStu courseStu)
    {
        List<CourseStu> list = courseStuService.selectCourseStuList(courseStu);
        ExcelUtil<CourseStu> util = new ExcelUtil<CourseStu>(CourseStu.class);
        return util.exportExcel(list, "stu");
    }

    /**
     * 新增学生课程信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存学生课程信息
     */
    @RequiresPermissions("system:stu:add")
    @Log(title = "学生课程信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CourseStu courseStu)
    {
        return toAjax(courseStuService.insertCourseStu(courseStu));
    }

    /**
     * 修改学生课程信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CourseStu courseStu = courseStuService.selectCourseStuById(id);
        mmap.put("courseStu", courseStu);
        return prefix + "/edit";
    }

    /**
     * 修改保存学生课程信息
     */
    @RequiresPermissions("system:stu:edit")
    @Log(title = "学生课程信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CourseStu courseStu)
    {
        return toAjax(courseStuService.updateCourseStu(courseStu));
    }

    /**
     * 删除学生课程信息
     */
    @RequiresPermissions("system:stu:remove")
    @Log(title = "学生课程信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(courseStuService.deleteCourseStuByIds(ids));
    }
}
