package edu.yctc.project.system.know.service;

import java.util.List;

import edu.yctc.project.system.know.domain.LessonKnow;

/**
 * 课时知识点信息Service接口
 * 
 * @author yctc
 * @date 2020-04-18
 */
public interface ILessonKnowService 
{
    /**
     * 查询课时知识点信息
     * 
     * @param id 课时知识点信息ID
     * @return 课时知识点信息
     */
    public LessonKnow selectLessonKnowById(Long id);

    /**
     * 查询课时知识点信息列表
     * 
     * @param lessonKnow 课时知识点信息
     * @return 课时知识点信息集合
     */
    public List<LessonKnow> selectLessonKnowList(LessonKnow lessonKnow);

    /**
     * 新增课时知识点信息
     * 
     * @param lessonKnow 课时知识点信息
     * @return 结果
     */
    public int insertLessonKnow(LessonKnow lessonKnow);

    /**
     * 修改课时知识点信息
     * 
     * @param lessonKnow 课时知识点信息
     * @return 结果
     */
    public int updateLessonKnow(LessonKnow lessonKnow);

    /**
     * 批量删除课时知识点信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLessonKnowByIds(String ids);

    /**
     * 删除课时知识点信息信息
     * 
     * @param id 课时知识点信息ID
     * @return 结果
     */
    public int deleteLessonKnowById(Long id);
}
