package edu.yctc.project.system.stu.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.stu.mapper.CourseStuMapper;
import edu.yctc.project.system.stu.domain.CourseStu;
import edu.yctc.project.system.stu.service.ICourseStuService;
import edu.yctc.common.utils.text.Convert;

/**
 * 学生课程信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-18
 */
@Service
public class CourseStuServiceImpl implements ICourseStuService 
{
    @Autowired
    private CourseStuMapper courseStuMapper;

    /**
     * 查询学生课程信息
     * 
     * @param id 学生课程信息ID
     * @return 学生课程信息
     */
    @Override
    public CourseStu selectCourseStuById(Long id)
    {
        return courseStuMapper.selectCourseStuById(id);
    }

    /**
     * 查询学生课程信息列表
     * 
     * @param courseStu 学生课程信息
     * @return 学生课程信息
     */
    @Override
    public List<CourseStu> selectCourseStuList(CourseStu courseStu)
    {
        return courseStuMapper.selectCourseStuList(courseStu);
    }

    /**
     * 新增学生课程信息
     * 
     * @param courseStu 学生课程信息
     * @return 结果
     */
    @Override
    public int insertCourseStu(CourseStu courseStu)
    {
        courseStu.setCreateTime(DateUtils.getNowDate());
        return courseStuMapper.insertCourseStu(courseStu);
    }

    /**
     * 修改学生课程信息
     * 
     * @param courseStu 学生课程信息
     * @return 结果
     */
    @Override
    public int updateCourseStu(CourseStu courseStu)
    {
        return courseStuMapper.updateCourseStu(courseStu);
    }

    /**
     * 删除学生课程信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCourseStuByIds(String ids)
    {
        return courseStuMapper.deleteCourseStuByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生课程信息信息
     * 
     * @param id 学生课程信息ID
     * @return 结果
     */
    @Override
    public int deleteCourseStuById(Long id)
    {
        return courseStuMapper.deleteCourseStuById(id);
    }
}
