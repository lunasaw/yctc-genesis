package edu.yctc.project.system.score.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.score.mapper.ClassScoreMapper;
import edu.yctc.project.system.score.domain.ClassScore;
import edu.yctc.project.system.score.service.IClassScoreService;
import edu.yctc.common.utils.text.Convert;

/**
 * 学生上课评分汇总Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class ClassScoreServiceImpl implements IClassScoreService 
{
    @Autowired
    private ClassScoreMapper classScoreMapper;

    /**
     * 查询学生上课评分汇总
     * 
     * @param id 学生上课评分汇总ID
     * @return 学生上课评分汇总
     */
    @Override
    public ClassScore selectClassScoreById(Long id)
    {
        return classScoreMapper.selectClassScoreById(id);
    }

    /**
     * 查询学生上课评分汇总列表
     * 
     * @param classScore 学生上课评分汇总
     * @return 学生上课评分汇总
     */
    @Override
    public List<ClassScore> selectClassScoreList(ClassScore classScore)
    {
        return classScoreMapper.selectClassScoreList(classScore);
    }

    /**
     * 新增学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    @Override
    public int insertClassScore(ClassScore classScore)
    {
        classScore.setCreateTime(DateUtils.getNowDate());
        return classScoreMapper.insertClassScore(classScore);
    }

    /**
     * 修改学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    @Override
    public int updateClassScore(ClassScore classScore)
    {
        return classScoreMapper.updateClassScore(classScore);
    }

    /**
     * 删除学生上课评分汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassScoreByIds(String ids)
    {
        return classScoreMapper.deleteClassScoreByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生上课评分汇总信息
     * 
     * @param id 学生上课评分汇总ID
     * @return 结果
     */
    @Override
    public int deleteClassScoreById(Long id)
    {
        return classScoreMapper.deleteClassScoreById(id);
    }
}
