package edu.yctc.project.system.knowledgeStudentState.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;
import edu.yctc.project.system.knowledgeStudentState.mapper.KnowledgeStudentStateMapper;
import edu.yctc.project.system.knowledgeStudentState.service.IKnowledgeStudentStateService;

/**
 * 知识点和学生状态关联Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Service
public class KnowledgeStudentStateServiceImpl implements IKnowledgeStudentStateService
{
    @Autowired
    private KnowledgeStudentStateMapper knowledgeStudentStateMapper;

    /**
     * 查询知识点和学生状态关联
     * 
     * @param id 知识点和学生状态关联ID
     * @return 知识点和学生状态关联
     */
    @Override
    public KnowledgeStudentState selectKnowledgeStudentStateById(Long id)
    {
        return knowledgeStudentStateMapper.selectKnowledgeStudentStateById(id);
    }

    /**
     * 查询知识点和学生状态关联列表
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 知识点和学生状态关联
     */
    @Override
    public List<KnowledgeStudentState> selectKnowledgeStudentStateList(KnowledgeStudentState knowledgeStudentState)
    {
        return knowledgeStudentStateMapper.selectKnowledgeStudentStateList(knowledgeStudentState);
    }

    /**
     * 新增知识点和学生状态关联
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 结果
     */
    @Override
    public int insertKnowledgeStudentState(KnowledgeStudentState knowledgeStudentState)
    {
        knowledgeStudentState.setCreateTime(DateUtils.getNowDate());
        return knowledgeStudentStateMapper.insertKnowledgeStudentState(knowledgeStudentState);
    }

    /**
     * 修改知识点和学生状态关联
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 结果
     */
    @Override
    public int updateKnowledgeStudentState(KnowledgeStudentState knowledgeStudentState)
    {
        return knowledgeStudentStateMapper.updateKnowledgeStudentState(knowledgeStudentState);
    }

    /**
     * 删除知识点和学生状态关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeStudentStateByIds(String ids)
    {
        return knowledgeStudentStateMapper.deleteKnowledgeStudentStateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除知识点和学生状态关联信息
     * 
     * @param id 知识点和学生状态关联ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeStudentStateById(Long id)
    {
        return knowledgeStudentStateMapper.deleteKnowledgeStudentStateById(id);
    }
}
