package edu.yctc.project.system.applyClassroom.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.applyClassroom.mapper.UserApplyclassroomMapper;
import edu.yctc.project.system.applyClassroom.domain.UserApplyclassroom;
import edu.yctc.project.system.applyClassroom.service.IUserApplyclassroomService;
import edu.yctc.common.utils.text.Convert;

/**
 * 教室申请Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-21
 */
@Service
public class UserApplyclassroomServiceImpl implements IUserApplyclassroomService 
{
    @Autowired
    private UserApplyclassroomMapper userApplyclassroomMapper;

    /**
     * 查询教室申请
     * 
     * @param id 教室申请ID
     * @return 教室申请
     */
    @Override
    public UserApplyclassroom selectUserApplyclassroomById(Long id)
    {
        return userApplyclassroomMapper.selectUserApplyclassroomById(id);
    }

    /**
     * 查询教室申请列表
     * 
     * @param userApplyclassroom 教室申请
     * @return 教室申请
     */
    @Override
    public List<UserApplyclassroom> selectUserApplyclassroomList(UserApplyclassroom userApplyclassroom)
    {
        return userApplyclassroomMapper.selectUserApplyclassroomList(userApplyclassroom);
    }

    /**
     * 新增教室申请
     * 
     * @param userApplyclassroom 教室申请
     * @return 结果
     */
    @Override
    public int insertUserApplyclassroom(UserApplyclassroom userApplyclassroom)
    {
        userApplyclassroom.setCreateTime(DateUtils.getNowDate());
        return userApplyclassroomMapper.insertUserApplyclassroom(userApplyclassroom);
    }

    /**
     * 修改教室申请
     * 
     * @param userApplyclassroom 教室申请
     * @return 结果
     */
    @Override
    public int updateUserApplyclassroom(UserApplyclassroom userApplyclassroom)
    {
        return userApplyclassroomMapper.updateUserApplyclassroom(userApplyclassroom);
    }

    /**
     * 删除教室申请对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserApplyclassroomByIds(String ids)
    {
        return userApplyclassroomMapper.deleteUserApplyclassroomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除教室申请信息
     * 
     * @param id 教室申请ID
     * @return 结果
     */
    @Override
    public int deleteUserApplyclassroomById(Long id)
    {
        return userApplyclassroomMapper.deleteUserApplyclassroomById(id);
    }
}
