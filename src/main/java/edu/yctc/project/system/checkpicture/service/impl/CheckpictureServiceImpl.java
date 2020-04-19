package edu.yctc.project.system.checkpicture.service.impl;

import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.checkpicture.domain.Checkpicture;
import edu.yctc.project.system.checkpicture.mapper.CheckpictureMapper;
import edu.yctc.project.system.checkpicture.service.ICheckpictureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 检测图片Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Service
public class CheckpictureServiceImpl implements ICheckpictureService 
{
    @Autowired
    private CheckpictureMapper checkpictureMapper;

    /**
     * 查询检测图片
     * 
     * @param id 检测图片ID
     * @return 检测图片
     */
    @Override
    public Checkpicture selectCheckpictureById(Integer id)
    {
        return checkpictureMapper.selectCheckpictureById(id);
    }

    /**
     * 查询检测图片列表
     * 
     * @param checkpicture 检测图片
     * @return 检测图片
     */
    @Override
    public List<Checkpicture> selectCheckpictureList(Checkpicture checkpicture)
    {
        return checkpictureMapper.selectCheckpictureList(checkpicture);
    }

    /**
     * 新增检测图片
     * 
     * @param checkpicture 检测图片
     * @return 结果
     */
    @Override
    public int insertCheckpicture(Checkpicture checkpicture)
    {
        return checkpictureMapper.insertCheckpicture(checkpicture);
    }

    /**
     * 修改检测图片
     * 
     * @param checkpicture 检测图片
     * @return 结果
     */
    @Override
    public int updateCheckpicture(Checkpicture checkpicture)
    {
        return checkpictureMapper.updateCheckpicture(checkpicture);
    }

    /**
     * 删除检测图片对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCheckpictureByIds(String ids)
    {
        return checkpictureMapper.deleteCheckpictureByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除检测图片信息
     * 
     * @param id 检测图片ID
     * @return 结果
     */
    @Override
    public int deleteCheckpictureById(Integer id)
    {
        return checkpictureMapper.deleteCheckpictureById(id);
    }
}
