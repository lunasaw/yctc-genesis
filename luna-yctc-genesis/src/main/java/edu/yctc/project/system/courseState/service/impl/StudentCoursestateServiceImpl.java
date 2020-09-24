package edu.yctc.project.system.courseState.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.courseState.domain.StudentCoursestate;
import edu.yctc.project.system.courseState.mapper.StudentCoursestateMapper;
import edu.yctc.project.system.courseState.service.IStudentCoursestateService;

/**
 * 学生上课状态信息汇总Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-17
 */
@Service
public class StudentCoursestateServiceImpl implements IStudentCoursestateService 
{
    @Autowired
    private StudentCoursestateMapper studentCoursestateMapper;

    /**
     * 查询学生上课状态信息汇总
     * 
     * @param id 学生上课状态信息汇总ID
     * @return 学生上课状态信息汇总
     */
    @Override
    public StudentCoursestate selectStudentCoursestateById(Long id)
    {
        return studentCoursestateMapper.selectStudentCoursestateById(id);
    }

    /**
     * 查询学生上课状态信息汇总列表
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 学生上课状态信息汇总
     */
    @Override
    public List<StudentCoursestate> selectStudentCoursestateList(StudentCoursestate studentCoursestate)
    {
        return studentCoursestateMapper.selectStudentCoursestateList(studentCoursestate);
    }

    /**
     * 新增学生上课状态信息汇总
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 结果
     */
    @Override
    public int insertStudentCoursestate(StudentCoursestate studentCoursestate)
    {
        studentCoursestate.setCreateTime(DateUtils.getNowDate());
        return studentCoursestateMapper.insertStudentCoursestate(studentCoursestate);
    }

    /**
     * 修改学生上课状态信息汇总
     * 
     * @param studentCoursestate 学生上课状态信息汇总
     * @return 结果
     */
    @Override
    public int updateStudentCoursestate(StudentCoursestate studentCoursestate)
    {
        return studentCoursestateMapper.updateStudentCoursestate(studentCoursestate);
    }

    /**
     * 删除学生上课状态信息汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteStudentCoursestateByIds(String ids)
    {
        return studentCoursestateMapper.deleteStudentCoursestateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生上课状态信息汇总信息
     * 
     * @param id 学生上课状态信息汇总ID
     * @return 结果
     */
    @Override
    public int deleteStudentCoursestateById(Long id)
    {
        return studentCoursestateMapper.deleteStudentCoursestateById(id);
    }
}
