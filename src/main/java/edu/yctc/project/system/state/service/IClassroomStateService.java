package edu.yctc.project.system.state.service;

import java.util.List;
import edu.yctc.project.system.state.domain.ClassroomState;

/**
 * 教室占用情况Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface IClassroomStateService 
{
    /**
     * 查询教室占用情况
     * 
     * @param id 教室占用情况ID
     * @return 教室占用情况
     */
    public ClassroomState selectClassroomStateById(Long id);

    /**
     * 查询教室占用情况列表
     * 
     * @param classroomState 教室占用情况
     * @return 教室占用情况集合
     */
    public List<ClassroomState> selectClassroomStateList(ClassroomState classroomState);

    /**
     * 新增教室占用情况
     * 
     * @param classroomState 教室占用情况
     * @return 结果
     */
    public int insertClassroomState(ClassroomState classroomState);

    /**
     * 修改教室占用情况
     * 
     * @param classroomState 教室占用情况
     * @return 结果
     */
    public int updateClassroomState(ClassroomState classroomState);

    /**
     * 批量删除教室占用情况
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassroomStateByIds(String ids);

    /**
     * 删除教室占用情况信息
     * 
     * @param id 教室占用情况ID
     * @return 结果
     */
    public int deleteClassroomStateById(Long id);
}
