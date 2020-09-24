package edu.yctc.project.system.lesson.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.lesson.mapper.LessonMapper;
import edu.yctc.project.system.lesson.service.ILessonService;

/**
 * 课时信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class LessonServiceImpl implements ILessonService 
{
    @Autowired
    private LessonMapper lessonMapper;

    /**
     * 查询课时信息
     * 
     * @param id 课时信息ID
     * @return 课时信息
     */
    @Override
    public Lesson selectLessonById(Long id)
    {
        return lessonMapper.selectLessonById(id);
    }

    /**
     * 查询课时信息列表
     * 
     * @param lesson 课时信息
     * @return 课时信息
     */
    @Override
    public List<Lesson> selectLessonList(Lesson lesson)
    {
        return lessonMapper.selectLessonList(lesson);
    }

    /**
     * 新增课时信息
     * 
     * @param lesson 课时信息
     * @return 结果
     */
    @Override
    public int insertLesson(Lesson lesson)
    {
        lesson.setCreateTime(DateUtils.getNowDate());
        return lessonMapper.insertLesson(lesson);
    }

    /**
     * 修改课时信息
     * 
     * @param lesson 课时信息
     * @return 结果
     */
    @Override
    public int updateLesson(Lesson lesson)
    {
        return lessonMapper.updateLesson(lesson);
    }

    /**
     * 删除课时信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLessonByIds(String ids)
    {
        return lessonMapper.deleteLessonByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课时信息信息
     * 
     * @param id 课时信息ID
     * @return 结果
     */
    @Override
    public int deleteLessonById(Long id)
    {
        return lessonMapper.deleteLessonById(id);
    }
}
