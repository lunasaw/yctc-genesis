package edu.yctc.project.system.lesson.service;

import java.util.List;

import edu.yctc.project.system.lesson.domain.Lesson;

/**
 * 课时信息Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface ILessonService 
{
    /**
     * 查询课时信息
     * 
     * @param id 课时信息ID
     * @return 课时信息
     */
    public Lesson selectLessonById(Long id);

    /**
     * 查询课时信息列表
     * 
     * @param lesson 课时信息
     * @return 课时信息集合
     */
    public List<Lesson> selectLessonList(Lesson lesson);

    /**
     * 新增课时信息
     * 
     * @param lesson 课时信息
     * @return 结果
     */
    public int insertLesson(Lesson lesson);

    /**
     * 修改课时信息
     * 
     * @param lesson 课时信息
     * @return 结果
     */
    public int updateLesson(Lesson lesson);

    /**
     * 批量删除课时信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLessonByIds(String ids);

    /**
     * 删除课时信息信息
     * 
     * @param id 课时信息ID
     * @return 结果
     */
    public int deleteLessonById(Long id);
}
