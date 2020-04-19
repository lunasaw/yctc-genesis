package edu.yctc.project.system.knowledgePicture.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.knowledgePicture.mapper.KnowledgePictureMapper;
import edu.yctc.project.system.knowledgePicture.domain.KnowledgePicture;
import edu.yctc.project.system.knowledgePicture.service.IKnowledgePictureService;
import edu.yctc.common.utils.text.Convert;

/**
 * 图片和知识点关联Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Service
public class KnowledgePictureServiceImpl implements IKnowledgePictureService 
{
    @Autowired
    private KnowledgePictureMapper knowledgePictureMapper;

    /**
     * 查询图片和知识点关联
     * 
     * @param id 图片和知识点关联ID
     * @return 图片和知识点关联
     */
    @Override
    public KnowledgePicture selectKnowledgePictureById(Long id)
    {
        return knowledgePictureMapper.selectKnowledgePictureById(id);
    }

    /**
     * 查询图片和知识点关联列表
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 图片和知识点关联
     */
    @Override
    public List<KnowledgePicture> selectKnowledgePictureList(KnowledgePicture knowledgePicture)
    {
        return knowledgePictureMapper.selectKnowledgePictureList(knowledgePicture);
    }

    /**
     * 新增图片和知识点关联
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 结果
     */
    @Override
    public int insertKnowledgePicture(KnowledgePicture knowledgePicture)
    {
        return knowledgePictureMapper.insertKnowledgePicture(knowledgePicture);
    }

    /**
     * 修改图片和知识点关联
     * 
     * @param knowledgePicture 图片和知识点关联
     * @return 结果
     */
    @Override
    public int updateKnowledgePicture(KnowledgePicture knowledgePicture)
    {
        return knowledgePictureMapper.updateKnowledgePicture(knowledgePicture);
    }

    /**
     * 删除图片和知识点关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgePictureByIds(String ids)
    {
        return knowledgePictureMapper.deleteKnowledgePictureByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除图片和知识点关联信息
     * 
     * @param id 图片和知识点关联ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgePictureById(Long id)
    {
        return knowledgePictureMapper.deleteKnowledgePictureById(id);
    }
}
