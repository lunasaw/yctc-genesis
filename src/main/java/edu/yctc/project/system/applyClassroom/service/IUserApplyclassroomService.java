package edu.yctc.project.system.applyClassroom.service;

import java.util.List;
import edu.yctc.project.system.applyClassroom.domain.UserApplyclassroom;

/**
 * 教室申请Service接口
 * 
 * @author yctc
 * @date 2020-04-21
 */
public interface IUserApplyclassroomService 
{
    /**
     * 查询教室申请
     * 
     * @param id 教室申请ID
     * @return 教室申请
     */
    public UserApplyclassroom selectUserApplyclassroomById(Long id);

    /**
     * 查询教室申请列表
     * 
     * @param userApplyclassroom 教室申请
     * @return 教室申请集合
     */
    public List<UserApplyclassroom> selectUserApplyclassroomList(UserApplyclassroom userApplyclassroom);

    /**
     * 新增教室申请
     * 
     * @param userApplyclassroom 教室申请
     * @return 结果
     */
    public int insertUserApplyclassroom(UserApplyclassroom userApplyclassroom);

    /**
     * 修改教室申请
     * 
     * @param userApplyclassroom 教室申请
     * @return 结果
     */
    public int updateUserApplyclassroom(UserApplyclassroom userApplyclassroom);

    /**
     * 批量删除教室申请
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserApplyclassroomByIds(String ids);

    /**
     * 删除教室申请信息
     * 
     * @param id 教室申请ID
     * @return 结果
     */
    public int deleteUserApplyclassroomById(Long id);
}
