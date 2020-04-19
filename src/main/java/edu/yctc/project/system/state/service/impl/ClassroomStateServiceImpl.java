package edu.yctc.project.system.state.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.state.mapper.ClassroomStateMapper;
import edu.yctc.project.system.state.domain.ClassroomState;
import edu.yctc.project.system.state.service.IClassroomStateService;
import edu.yctc.common.utils.text.Convert;

/**
 * 教室占用情况Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class ClassroomStateServiceImpl implements IClassroomStateService 
{
    @Autowired
    private ClassroomStateMapper classroomStateMapper;

    /**
     * 查询教室占用情况
     * 
     * @param id 教室占用情况ID
     * @return 教室占用情况
     */
    @Override
    public ClassroomState selectClassroomStateById(Long id)
    {
        return classroomStateMapper.selectClassroomStateById(id);
    }

    /**
     * 查询教室占用情况列表
     * 
     * @param classroomState 教室占用情况
     * @return 教室占用情况
     */
    @Override
    public List<ClassroomState> selectClassroomStateList(ClassroomState classroomState)
    {
        return classroomStateMapper.selectClassroomStateList(classroomState);
    }

    /**
     * 新增教室占用情况
     * 
     * @param classroomState 教室占用情况
     * @return 结果
     */
    @Override
    public int insertClassroomState(ClassroomState classroomState)
    {
        classroomState.setCreateTime(DateUtils.getNowDate());
        return classroomStateMapper.insertClassroomState(classroomState);
    }

    /**
     * 修改教室占用情况
     * 
     * @param classroomState 教室占用情况
     * @return 结果
     */
    @Override
    public int updateClassroomState(ClassroomState classroomState)
    {
        return classroomStateMapper.updateClassroomState(classroomState);
    }

    /**
     * 删除教室占用情况对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassroomStateByIds(String ids)
    {
        return classroomStateMapper.deleteClassroomStateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除教室占用情况信息
     * 
     * @param id 教室占用情况ID
     * @return 结果
     */
    @Override
    public int deleteClassroomStateById(Long id)
    {
        return classroomStateMapper.deleteClassroomStateById(id);
    }
}
