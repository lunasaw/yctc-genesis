package edu.yctc.project.system.knowledgeStudentState.mapper;

import edu.yctc.project.system.knowledgeStudentState.domain.KnowledgeStudentState;

import java.util.List;

/**
 * 知识点和学生状态关联Mapper接口
 * 
 * @author yctc
 * @date 2020-04-19
 */
public interface KnowledgeStudentStateMapper 
{
    /**
     * 查询知识点和学生状态关联
     * 
     * @param id 知识点和学生状态关联ID
     * @return 知识点和学生状态关联
     */
    public KnowledgeStudentState selectKnowledgeStudentStateById(Long id);

    /**
     * 查询知识点和学生状态关联列表
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 知识点和学生状态关联集合
     */
    public List<KnowledgeStudentState> selectKnowledgeStudentStateList(KnowledgeStudentState knowledgeStudentState);

    /**
     * 新增知识点和学生状态关联
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 结果
     */
    public int insertKnowledgeStudentState(KnowledgeStudentState knowledgeStudentState);

    /**
     * 修改知识点和学生状态关联
     * 
     * @param knowledgeStudentState 知识点和学生状态关联
     * @return 结果
     */
    public int updateKnowledgeStudentState(KnowledgeStudentState knowledgeStudentState);

    /**
     * 删除知识点和学生状态关联
     * 
     * @param id 知识点和学生状态关联ID
     * @return 结果
     */
    public int deleteKnowledgeStudentStateById(Long id);

    /**
     * 批量删除知识点和学生状态关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKnowledgeStudentStateByIds(String[] ids);
}
