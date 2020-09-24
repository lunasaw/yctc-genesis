package edu.yctc.project.system.knowledgePicture.service;

import java.util.List;

import edu.yctc.project.system.knowledgePicture.domain.KnowledgePicture;

/**
 * 图片和知识点关联Service接口
 * 
 * @author yctc
 * @date 2020-04-19
 */
public interface IKnowledgePictureService 
{
    /**
     * 查询图片和知识点关联
     * 
     * @param id 图片和知识点关联ID
     * @return 图片和知识点关联
     */
    public KnowledgePicture selectKnowledgePictureById(Long id);

    /**
     * 查询图片和知识点关联列表
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 图片和知识点关联集合
     */
    public List<KnowledgePicture> selectKnowledgePictureList(KnowledgePicture knowledgePicture);

    /**
     * 新增图片和知识点关联
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 结果
     */
    public int insertKnowledgePicture(KnowledgePicture knowledgePicture);

    /**
     * 修改图片和知识点关联
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 结果
     */
    public int updateKnowledgePicture(KnowledgePicture knowledgePicture);

    /**
     * 批量删除图片和知识点关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteKnowledgePictureByIds(String ids);

    /**
     * 删除图片和知识点关联信息
     * 
     * @param id 图片和知识点关联ID
     * @return 结果
     */
    public int deleteKnowledgePictureById(Long id);
}
