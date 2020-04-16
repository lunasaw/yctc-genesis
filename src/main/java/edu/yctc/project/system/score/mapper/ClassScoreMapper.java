package edu.yctc.project.system.score.mapper;

import java.util.List;
import edu.yctc.project.system.score.domain.ClassScore;

/**
 * 学生上课评分汇总Mapper接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface ClassScoreMapper 
{
    /**
     * 查询学生上课评分汇总
     * 
     * @param id 学生上课评分汇总ID
     * @return 学生上课评分汇总
     */
    public ClassScore selectClassScoreById(Long id);

    /**
     * 查询学生上课评分汇总列表
     * 
     * @param classScore 学生上课评分汇总
     * @return 学生上课评分汇总集合
     */
    public List<ClassScore> selectClassScoreList(ClassScore classScore);

    /**
     * 新增学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    public int insertClassScore(ClassScore classScore);

    /**
     * 修改学生上课评分汇总
     * 
     * @param classScore 学生上课评分汇总
     * @return 结果
     */
    public int updateClassScore(ClassScore classScore);

    /**
     * 删除学生上课评分汇总
     * 
     * @param id 学生上课评分汇总ID
     * @return 结果
     */
    public int deleteClassScoreById(Long id);

    /**
     * 批量删除学生上课评分汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassScoreByIds(String[] ids);
}
