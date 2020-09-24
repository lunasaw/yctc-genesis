package edu.yctc.project.system.knowledge.service;

import java.util.List;

import edu.yctc.project.system.knowledge.domain.Knowledge;

/**
 * 知识点概览Service接口
 * 
 * @author yctc
 * @date 2020-04-21
 */
public interface IKnowledgeService 
{
    /**
     * 查询知识点概览
     * 
     * @param id 知识点概览ID
     * @return 知识点概览
     */
    public Knowledge selectKnowledgeById(Long id);

    /**
     * 查询知识点概览列表
     * 
     * @param knowledge 知识点概览
     * @return 知识点概览集合
     */
    public List<Knowledge> selectKnowledgeList(Knowledge knowledge);

    /**
     * 新增知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    public int insertKnowledge(Knowledge knowledge);

    /**
     * 修改知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    public int updateKnowledge(Knowledge knowledge);

    /**
     * 批量删除知识点概览
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKnowledgeByIds(String ids);

    /**
     * 删除知识点概览信息
     * 
     * @param id 知识点概览ID
     * @return 结果
     */
    public int deleteKnowledgeById(Long id);


	/**
	 * 判断待匹配字符串中是否存在知识点
	 *
	 * @param knowledge
	 * @param toMatch
	 * @return
	 */
	public boolean checkKnowledge(String knowledge, String toMatch);
}
