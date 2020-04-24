package edu.yctc.project.teacherLesson;

import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;
import edu.yctc.project.system.knowledgeStudentState.service.IKnowledgeStudentStateService;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Luna@win10
 * @date 2020/4/24 12:42
 */
@Controller
@RequestMapping("/system/teacherKnowledgeState")
public class TeacherKnowledgeStateController extends BaseController {

	private String prefix = "system/teacherKnowledgeState";

	@Autowired
	private IKnowledgeStudentStateService knowledgeStudentStateService;

	@Autowired
	private ILessonTeaService lessonTeaService;

	@RequiresPermissions("system:teacherKnowledgeState:view")
	@GetMapping()
	public String knowledgeStudentState()
	{
		return prefix + "/knowledgeStudentState";
	}

	/**
	 * 查询知识点和学生状态关联列表
	 */
	@RequiresPermissions("system:teacherKnowledgeState:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(KnowledgeStudentState knowledgeStudentState)
	{
		Long userId = ShiroUtils.getUserId();
		LessonTea lessonTea = new LessonTea();
		lessonTea.setUserId(userId);
		List<LessonTea> lessonTeas = lessonTeaService.selectLessonTeaList(lessonTea);
		if (lessonTeas.size() == 0) {
			return getDataTable(new ArrayList<ClassScore>());
		}
		lessonTea.setUserId(userId);
		List<LessonTea> lessonTeas1 = lessonTeaService.selectLessonTeaList(lessonTea);
		List<KnowledgeStudentState> knowledgeStudentStates=new ArrayList<>();
		List<KnowledgeStudentState> list=new ArrayList<>();
		for (int i = 0; i < lessonTeas1.size(); i++) {
			knowledgeStudentState.setLessonId(lessonTeas1.get(i).getLessonId());
			knowledgeStudentStates= knowledgeStudentStateService.selectKnowledgeStudentStateList(knowledgeStudentState);
			list.addAll(knowledgeStudentStates);
		}
//		startPage();
//		List<KnowledgeStudentState> list = knowledgeStudentStateService.selectKnowledgeStudentStateList(knowledgeStudentState);
		return getDataTable(list);
	}

	/**
	 * 导出知识点和学生状态关联列表
	 */
	@RequiresPermissions("system:teacherKnowledgeState:export")
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
	@RequiresPermissions("system:teacherKnowledgeState:add")
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
	@RequiresPermissions("system:teacherKnowledgeState:edit")
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
	@RequiresPermissions("system:teacherKnowledgeState:remove")
	@Log(title = "知识点和学生状态关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(knowledgeStudentStateService.deleteKnowledgeStudentStateByIds(ids));
	}
}
