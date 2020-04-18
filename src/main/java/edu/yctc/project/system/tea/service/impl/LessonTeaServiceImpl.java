package edu.yctc.project.system.tea.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.tea.mapper.LessonTeaMapper;
import edu.yctc.project.system.tea.domain.LessonTea;
import edu.yctc.project.system.tea.service.ILessonTeaService;
import edu.yctc.common.utils.text.Convert;

/**
 * 老师授课信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Service
public class LessonTeaServiceImpl implements ILessonTeaService 
{
    @Autowired
    private LessonTeaMapper lessonTeaMapper;

    /**
     * 查询老师授课信息
     * 
     * @param id 老师授课信息ID
     * @return 老师授课信息
     */
    @Override
    public LessonTea selectLessonTeaById(Long id)
    {
        return lessonTeaMapper.selectLessonTeaById(id);
    }

    /**
     * 查询老师授课信息列表
     * 
     * @param lessonTea 老师授课信息
     * @return 老师授课信息
     */
    @Override
    public List<LessonTea> selectLessonTeaList(LessonTea lessonTea)
    {
        return lessonTeaMapper.selectLessonTeaList(lessonTea);
    }

    /**
     * 新增老师授课信息
     * 
     * @param lessonTea 老师授课信息
     * @return 结果
     */
    @Override
    public int insertLessonTea(LessonTea lessonTea)
    {
        lessonTea.setCreateTime(DateUtils.getNowDate());
        return lessonTeaMapper.insertLessonTea(lessonTea);
    }

    /**
     * 修改老师授课信息
     * 
     * @param lessonTea 老师授课信息
     * @return 结果
     */
    @Override
    public int updateLessonTea(LessonTea lessonTea)
    {
        return lessonTeaMapper.updateLessonTea(lessonTea);
    }

    /**
     * 删除老师授课信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLessonTeaByIds(String ids)
    {
        return lessonTeaMapper.deleteLessonTeaByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除老师授课信息信息
     * 
     * @param id 老师授课信息ID
     * @return 结果
     */
    @Override
    public int deleteLessonTeaById(Long id)
    {
        return lessonTeaMapper.deleteLessonTeaById(id);
    }
}
