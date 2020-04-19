package edu.yctc.project.system.stu.mapper;

import java.util.List;
import edu.yctc.project.system.stu.domain.CourseStu;

/**
 * 学生课程信息Mapper接口
 * 
 * @author yctc
 * @date 2020-04-18
 */
public interface CourseStuMapper 
{
    /**
     * 查询学生课程信息
     * 
     * @param id 学生课程信息ID
     * @return 学生课程信息
     */
    public CourseStu selectCourseStuById(Long id);

    /**
     * 查询学生课程信息列表
     * 
     * @param courseStu 学生课程信息
     * @return 学生课程信息集合
     */
    public List<CourseStu> selectCourseStuList(CourseStu courseStu);

    /**
     * 新增学生课程信息
     * 
     * @param courseStu 学生课程信息
     * @return 结果
     */
    public int insertCourseStu(CourseStu courseStu);

    /**
     * 修改学生课程信息
     * 
     * @param courseStu 学生课程信息
     * @return 结果
     */
    public int updateCourseStu(CourseStu courseStu);

    /**
     * 删除学生课程信息
     * 
     * @param id 学生课程信息ID
     * @return 结果
     */
    public int deleteCourseStuById(Long id);

    /**
     * 批量删除学生课程信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCourseStuByIds(String[] ids);
}
