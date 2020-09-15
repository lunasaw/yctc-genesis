package edu.yctc.project.system.courseState.mapper;

import java.util.List;

import edu.yctc.project.system.courseState.domain.StudentCoursestate;

/**
 * 学生上课状态信息汇总Mapper接口
 * 
 * @author yctc
 * @date 2020-04-17
 */
public interface StudentCoursestateMapper 
{
    /**
     * 查询学生上课状态信息汇总
     * 
     * @param id 学生上课状态信息汇总ID
     * @return 学生上课状态信息汇总
     */
    public StudentCoursestate selectStudentCoursestateById(Long id);

    /**
     * 查询学生上课状态信息汇总列表
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 学生上课状态信息汇总集合
     */
    public List<StudentCoursestate> selectStudentCoursestateList(StudentCoursestate studentCoursestate);

    /**
     * 新增学生上课状态信息汇总
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 结果
     */
    public int insertStudentCoursestate(StudentCoursestate studentCoursestate);

    /**
     * 修改学生上课状态信息汇总
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 结果
     */
    public int updateStudentCoursestate(StudentCoursestate studentCoursestate);

    /**
     * 删除学生上课状态信息汇总
     * 
     * @param id 学生上课状态信息汇总ID
     * @return 结果
     */
    public int deleteStudentCoursestateById(Long id);

    /**
     * 批量删除学生上课状态信息汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteStudentCoursestateByIds(String[] ids);
}
