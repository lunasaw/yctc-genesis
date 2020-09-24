package edu.yctc.project.system.info.mapper;

import java.util.List;

import edu.yctc.project.system.info.domain.FileInfo;

/**
 * 文件信息Mapper接口
 * 
 * @author luna
 * @date 2020-03-28
 */
public interface FileInfoMapper 
{
    /**
     * 查询文件信息
     * 
     * @param fileId 文件信息ID
     * @return 文件信息
     */
    public FileInfo selectFileInfoById(Long fileId);

    /**
     * 查询文件信息列表
     * 
     * @param fileInfo 文件信息
     * @return 文件信息集合
     */
    public List<FileInfo> selectFileInfoList(FileInfo fileInfo);

    /**
     * 新增文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    public int insertFileInfo(FileInfo fileInfo);

    /**
     * 修改文件信息
     * 
     * @param fileInfo 文件信息
     * @return 结果
     */
    public int updateFileInfo(FileInfo fileInfo);

    /**
     * 删除文件信息
     * 
     * @param fileId 文件信息ID
     * @return 结果
     */
    public int deleteFileInfoById(Long fileId);

    /**
     * 批量删除文件信息
     * 
     * @param fileIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteFileInfoByIds(String[] fileIds);
}
