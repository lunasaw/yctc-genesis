package edu.yctc.project.teacherLesson;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import edu.yctc.common.utils.poi.ExcelUtil;
import edu.yctc.common.utils.security.ShiroUtils;
import edu.yctc.framework.aspectj.lang.annotation.Log;
import edu.yctc.framework.aspectj.lang.enums.BusinessType;
import edu.yctc.framework.web.controller.BaseController;
import edu.yctc.framework.web.domain.AjaxResult;
import edu.yctc.framework.web.page.TableDataInfo;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.service.IStudentCoursestateService;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;

/**
 * @author Luna@win10
 * @date 2020/4/24 13:16
 */
@Controller
@RequestMapping("/system/teacherLessonState")
public class TeacherLessonStateController extends BaseController {
	private String prefix = "system/teacherLessonState";

	@Autowired
	private IStudentCoursestateService studentCoursestateService;
	@Autowired
	private ILessonTeaService lessonTeaService;

	@RequiresPermissions("system:teacherLessonState:view")
	@GetMapping()
	public String courseState()
	{
		return prefix + "/courseState";
	}

	/**
	 * 查询学生上课状态信息汇总列表
	 */
	@RequiresPermissions("system:teacherLessonState:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(StudentCoursestate studentCoursestate)
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
		List<StudentCoursestate> list=new ArrayList<>();
		for (int i = 0; i < lessonTeas1.size(); i++) {
			studentCoursestate.setLessonId(lessonTeas1.get(i).getLessonId());
			List<StudentCoursestate> studentCoursestates = studentCoursestateService.selectStudentCoursestateList(studentCoursestate);
			list.addAll(studentCoursestates);
		}

//		startPage();
//		List<StudentCoursestate> list = studentCoursestateService.selectStudentCoursestateList(studentCoursestate);
		return getDataTable(list);
	}

	/**
	 * 导出学生上课状态信息汇总列表
	 */
	@RequiresPermissions("system:teacherLessonState:export")
	@Log(title = "学生上课状态信息汇总", businessType = BusinessType.EXPORT)
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(StudentCoursestate studentCoursestate)
	{
		List<StudentCoursestate> list = studentCoursestateService.selectStudentCoursestateList(studentCoursestate);
		ExcelUtil<StudentCoursestate> util = new ExcelUtil<StudentCoursestate>(StudentCoursestate.class);
		return util.exportExcel(list, "courseState");
	}

	/**
	 * 新增学生上课状态信息汇总
	 */
	@GetMapping("/add")
	public String add()
	{
		return prefix + "/add";
	}

	/**
	 * 新增保存学生上课状态信息汇总
	 */
	@RequiresPermissions("system:teacherLessonState:add")
	@Log(title = "学生上课状态信息汇总", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(StudentCoursestate studentCoursestate)
	{
		return toAjax(studentCoursestateService.insertStudentCoursestate(studentCoursestate));
	}

	/**
	 * 修改学生上课状态信息汇总
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		StudentCoursestate studentCoursestate = studentCoursestateService.selectStudentCoursestateById(id);
		mmap.put("studentCoursestate", studentCoursestate);
		return prefix + "/edit";
	}

	/**
	 * 修改保存学生上课状态信息汇总
	 */
	@RequiresPermissions("system:teacherLessonState:edit")
	@Log(title = "学生上课状态信息汇总", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(StudentCoursestate studentCoursestate)
	{
		return toAjax(studentCoursestateService.updateStudentCoursestate(studentCoursestate));
	}

	/**
	 * 删除学生上课状态信息汇总
	 */
	@RequiresPermissions("system:teacherLessonState:remove")
	@Log(title = "学生上课状态信息汇总", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{
		return toAjax(studentCoursestateService.deleteStudentCoursestateByIds(ids));
	}

}
