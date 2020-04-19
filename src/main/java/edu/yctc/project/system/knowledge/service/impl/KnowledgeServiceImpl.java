package edu.yctc.project.system.knowledge.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.knowledge.mapper.KnowledgeMapper;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;
import edu.yctc.common.utils.text.Convert;

/**
 * 知识点概览Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class KnowledgeServiceImpl implements IKnowledgeService 
{
    @Autowired
    private KnowledgeMapper knowledgeMapper;

    /**
     * 查询知识点概览
     * 
     * @param id 知识点概览ID
     * @return 知识点概览
     */
    @Override
    public Knowledge selectKnowledgeById(Long id)
    {
        return knowledgeMapper.selectKnowledgeById(id);
    }

    /**
     * 查询知识点概览列表
     * 
     * @param knowledge 知识点概览
     * @return 知识点概览
     */
    @Override
    public List<Knowledge> selectKnowledgeList(Knowledge knowledge)
    {
        return knowledgeMapper.selectKnowledgeList(knowledge);
    }

    /**
     * 新增知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    @Override
    public int insertKnowledge(Knowledge knowledge)
    {
        knowledge.setCreateTime(DateUtils.getNowDate());
        return knowledgeMapper.insertKnowledge(knowledge);
    }

    /**
     * 修改知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    @Override
    public int updateKnowledge(Knowledge knowledge)
    {
        return knowledgeMapper.updateKnowledge(knowledge);
    }

    /**
     * 删除知识点概览对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeByIds(String ids)
    {
        return knowledgeMapper.deleteKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除知识点概览信息
     * 
     * @param id 知识点概览ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeById(Long id)
    {
        return knowledgeMapper.deleteKnowledgeById(id);
    }
}
