package edu.yctc.project.system.classroom.mapper;

import java.util.List;

import edu.yctc.project.system.classroom.domain.Classroom;

/**
 * 教室Mapper接口
 * 
 * @author luna
 * @date 2020-04-15
 */
public interface ClassroomMapper 
{
    /**
     * 查询教室
     * 
     * @param id 教室ID
     * @return 教室
     */
    public Classroom selectClassroomById(Long id);

    /**
     * 查询教室列表
     * 
     * @param classroom 教室
     * @return 教室集合
     */
    public List<Classroom> selectClassroomList(Classroom classroom);

    /**
     * 新增教室
     * 
     * @param classroom 教室
     * @return 结果
     */
    public int insertClassroom(Classroom classroom);

    /**
     * 修改教室
     * 
     * @param classroom 教室
     * @return 结果
     */
    public int updateClassroom(Classroom classroom);

    /**
     * 删除教室
     * 
     * @param id 教室ID
     * @return 结果
     */
    public int deleteClassroomById(Long id);

    /**
     * 批量删除教室
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassroomByIds(String[] ids);
}
