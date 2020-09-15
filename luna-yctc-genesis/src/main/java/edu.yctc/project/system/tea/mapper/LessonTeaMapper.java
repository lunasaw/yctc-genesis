package edu.yctc.project.system.tea.mapper;

import java.util.List;

import edu.yctc.project.system.tea.domain.LessonTea;

/**
 * 老师授课信息Mapper接口
 * 
 * @author yctc
 * @date 2020-04-18
 */
public interface LessonTeaMapper 
{
    /**
     * 查询老师授课信息
     * 
     * @param id 老师授课信息ID
     * @return 老师授课信息
     */
    public LessonTea selectLessonTeaById(Long id);

    /**
     * 查询老师授课信息列表
     * 
     * @param lessonTea 老师授课信息
     * @return 老师授课信息集合
     */
    public List<LessonTea> selectLessonTeaList(LessonTea lessonTea);

    /**
     * 新增老师授课信息
     * 
     * @param lessonTea 老师授课信息
     * @return 结果
     */
    public int insertLessonTea(LessonTea lessonTea);

    /**
     * 修改老师授课信息
     * 
     * @param lessonTea 老师授课信息
     * @return 结果
     */
    public int updateLessonTea(LessonTea lessonTea);

    /**
     * 删除老师授课信息
     * 
     * @param id 老师授课信息ID
     * @return 结果
     */
    public int deleteLessonTeaById(Long id);

    /**
     * 批量删除老师授课信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteLessonTeaByIds(String[] ids);
}
