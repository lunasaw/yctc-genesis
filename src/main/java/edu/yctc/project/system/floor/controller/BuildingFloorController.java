package edu.yctc.project.system.floor.controller;

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
import edu.yctc.project.system.floor.domain.BuildingFloor;
import edu.yctc.project.system.floor.service.IBuildingFloorService;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.framework.web.page.TableDataInfo;

/**
 * 楼层信息Controller
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Controller
@RequestMapping("/system/floor")
public class BuildingFloorController extends BaseController
{
    private String prefix = "system/floor";

    @Autowired
    private IBuildingFloorService buildingFloorService;

    @RequiresPermissions("system:floor:view")
    @GetMapping()
    public String floor()
    {
        return prefix + "/floor";
    }

    /**
     * 查询楼层信息列表
     */
    @RequiresPermissions("system:floor:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BuildingFloor buildingFloor)
    {
        startPage();
        List<BuildingFloor> list = buildingFloorService.selectBuildingFloorList(buildingFloor);
        return getDataTable(list);
    }

    /**
     * 导出楼层信息列表
     */
    @RequiresPermissions("system:floor:export")
    @Log(title = "楼层信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BuildingFloor buildingFloor)
    {
        List<BuildingFloor> list = buildingFloorService.selectBuildingFloorList(buildingFloor);
        ExcelUtil<BuildingFloor> util = new ExcelUtil<BuildingFloor>(BuildingFloor.class);
        return util.exportExcel(list, "floor");
    }

    /**
     * 新增楼层信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存楼层信息
     */
    @RequiresPermissions("system:floor:add")
    @Log(title = "楼层信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BuildingFloor buildingFloor)
    {
        return toAjax(buildingFloorService.insertBuildingFloor(buildingFloor));
    }

    /**
     * 修改楼层信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BuildingFloor buildingFloor = buildingFloorService.selectBuildingFloorById(id);
        mmap.put("buildingFloor", buildingFloor);
        return prefix + "/edit";
    }

    /**
     * 修改保存楼层信息
     */
    @RequiresPermissions("system:floor:edit")
    @Log(title = "楼层信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BuildingFloor buildingFloor)
    {
        return toAjax(buildingFloorService.updateBuildingFloor(buildingFloor));
    }

    /**
     * 删除楼层信息
     */
    @RequiresPermissions("system:floor:remove")
    @Log(title = "楼层信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(buildingFloorService.deleteBuildingFloorByIds(ids));
    }
}
