package edu.yctc.project.system.course.service;

import java.util.List;

import edu.yctc.project.system.course.domain.Course;

/**
 * 课程信息Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface ICourseService 
{
    /**
     * 查询课程信息
     * 
     * @param id 课程信息ID
     * @return 课程信息
     */
    public Course selectCourseById(Long id);

    /**
     * 查询课程信息列表
     * 
     * @param course 课程信息
     * @return 课程信息集合
     */
    public List<Course> selectCourseList(Course course);

    /**
     * 新增课程信息
     * 
     * @param course 课程信息
     * @return 结果
     */
    public int insertCourse(Course course);

    /**
     * 修改课程信息
     * 
     * @param course 课程信息
     * @return 结果
     */
    public int updateCourse(Course course);

    /**
     * 批量删除课程信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseByIds(String ids);

    /**
     * 删除课程信息信息
     * 
     * @param id 课程信息ID
     * @return 结果
     */
    public int deleteCourseById(Long id);
}
