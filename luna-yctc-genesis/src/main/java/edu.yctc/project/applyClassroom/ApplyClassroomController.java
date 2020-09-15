package edu.yctc.project.applyClassroom;

import java.util.List;

import javax.annotation.Resource;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.service.IClassroomStateService;

/**
 * 老师教室申请
 *
 * @author Luna@win10
 * @date 2020/4/21 14:37
 */

@Controller
@RequestMapping("/system/apply")
public class ApplyClassroomController extends BaseController {
	@Resource
	private ApplyService applyService;

	@Autowired
	private IClassroomStateService classroomStateService;

	private String prefix = "system/apply";

	@RequiresPermissions("system:tea:view")
	@GetMapping()
	public String apply()
	{
		return prefix + "/apply";
	}

	@RequiresPermissions("system:tea:add")
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存教室占用情况
	 */
	@RequiresPermissions("system:tea:add")
	@Log(title = "教室申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ApplyVO applyVO)
	{
		System.out.println(applyVO);
		return toAjax(applyService.addApply(applyVO));
	}


	/**
	 * 查询教室占用情况列表
	 */
	@RequiresPermissions("system:tea:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ClassroomState classroomState)
	{
		List<ClassroomState> list = classroomStateService.selectClassroomStateList(classroomState);
		return getDataTable(list);
	}
}
