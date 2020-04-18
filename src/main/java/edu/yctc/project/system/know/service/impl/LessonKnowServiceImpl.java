package edu.yctc.project.system.know.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.know.mapper.LessonKnowMapper;
import edu.yctc.project.system.know.domain.LessonKnow;
import edu.yctc.project.system.know.service.ILessonKnowService;
import edu.yctc.common.utils.text.Convert;

/**
 * 课时知识点信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Service
public class LessonKnowServiceImpl implements ILessonKnowService 
{
    @Autowired
    private LessonKnowMapper lessonKnowMapper;

    /**
     * 查询课时知识点信息
     * 
     * @param id 课时知识点信息ID
     * @return 课时知识点信息
     */
    @Override
    public LessonKnow selectLessonKnowById(Long id)
    {
        return lessonKnowMapper.selectLessonKnowById(id);
    }

    /**
     * 查询课时知识点信息列表
     * 
     * @param lessonKnow 课时知识点信息
     * @return 课时知识点信息
     */
    @Override
    public List<LessonKnow> selectLessonKnowList(LessonKnow lessonKnow)
    {
        return lessonKnowMapper.selectLessonKnowList(lessonKnow);
    }

    /**
     * 新增课时知识点信息
     * 
     * @param lessonKnow 课时知识点信息
     * @return 结果
     */
    @Override
    public int insertLessonKnow(LessonKnow lessonKnow)
    {
        lessonKnow.setCreateTime(DateUtils.getNowDate());
        return lessonKnowMapper.insertLessonKnow(lessonKnow);
    }

    /**
     * 修改课时知识点信息
     * 
     * @param lessonKnow 课时知识点信息
     * @return 结果
     */
    @Override
    public int updateLessonKnow(LessonKnow lessonKnow)
    {
        return lessonKnowMapper.updateLessonKnow(lessonKnow);
    }

    /**
     * 删除课时知识点信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteLessonKnowByIds(String ids)
    {
        return lessonKnowMapper.deleteLessonKnowByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除课时知识点信息信息
     * 
     * @param id 课时知识点信息ID
     * @return 结果
     */
    @Override
    public int deleteLessonKnowById(Long id)
    {
        return lessonKnowMapper.deleteLessonKnowById(id);
    }
}
