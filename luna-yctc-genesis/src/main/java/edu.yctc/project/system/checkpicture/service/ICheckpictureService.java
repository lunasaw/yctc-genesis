package edu.yctc.project.system.checkpicture.service;

import java.util.List;

import edu.yctc.project.system.checkpicture.domain.Checkpicture;

/**
 * 检测图片Service接口
 * 
 * @author yctc
 * @date 2020-04-19
 */
public interface ICheckpictureService 
{
    /**
     * 查询检测图片
     * 
     * @param id 检测图片ID
     * @return 检测图片
     */
    public Checkpicture selectCheckpictureById(Integer id);

    /**
     * 查询检测图片列表
     * 
     * @param checkpicture 检测图片
     * @return 检测图片集合
     */
    public List<Checkpicture> selectCheckpictureList(Checkpicture checkpicture);

    /**
     * 新增检测图片
     * 
     * @param checkpicture 检测图片
     * @return 结果
     */
    public int insertCheckpicture(Checkpicture checkpicture);

    /**
     * 修改检测图片
     * 
     * @param checkpicture 检测图片
     * @return 结果
     */
    public int updateCheckpicture(Checkpicture checkpicture);

    /**
     * 批量删除检测图片
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCheckpictureByIds(String ids);

    /**
     * 删除检测图片信息
     * 
     * @param id 检测图片ID
     * @return 结果
     */
    public int deleteCheckpictureById(Integer id);
}
