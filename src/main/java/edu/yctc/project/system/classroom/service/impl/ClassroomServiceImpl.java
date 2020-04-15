package edu.yctc.project.system.classroom.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.classroom.mapper.ClassroomMapper;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.classroom.service.IClassroomService;

/**
 * 教室Service业务层处理
 * 
 * @author luna
 * @date 2020-04-15
 */
@Service
public class ClassroomServiceImpl implements IClassroomService 
{
    @Autowired
    private ClassroomMapper classroomMapper;

    /**
     * 查询教室
     * 
     * @param id 教室ID
     * @return 教室
     */
    @Override
    public Classroom selectClassroomById(Long id)
    {
        return classroomMapper.selectClassroomById(id);
    }

    /**
     * 查询教室列表
     * 
     * @param classroom 教室
     * @return 教室
     */
    @Override
    public List<Classroom> selectClassroomList(Classroom classroom)
    {
        return classroomMapper.selectClassroomList(classroom);
    }

    /**
     * 新增教室
     * 
     * @param classroom 教室
     * @return 结果
     */
    @Override
    public int insertClassroom(Classroom classroom)
    {
        classroom.setCreateTime(DateUtils.getNowDate());
        return classroomMapper.insertClassroom(classroom);
    }

    /**
     * 修改教室
     * 
     * @param classroom 教室
     * @return 结果
     */
    @Override
    public int updateClassroom(Classroom classroom)
    {
        return classroomMapper.updateClassroom(classroom);
    }

    /**
     * 删除教室对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassroomByIds(String ids)
    {
        return classroomMapper.deleteClassroomByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除教室信息
     * 
     * @param id 教室ID
     * @return 结果
     */
    @Override
    public int deleteClassroomById(Long id)
    {
        return classroomMapper.deleteClassroomById(id);
    }
}
