package edu.yctc.project.system.attendance.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.attendance.domain.Attendance;
import edu.yctc.project.system.attendance.mapper.AttendanceMapper;
import edu.yctc.project.system.attendance.service.IAttendanceService;

/**
 * 学生考勤点名汇总Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class AttendanceServiceImpl implements IAttendanceService 
{
    @Autowired
    private AttendanceMapper attendanceMapper;

    /**
     * 查询学生考勤点名汇总
     * 
     * @param id 学生考勤点名汇总ID
     * @return 学生考勤点名汇总
     */
    @Override
    public Attendance selectAttendanceById(Long id)
    {
        return attendanceMapper.selectAttendanceById(id);
    }

    /**
     * 查询学生考勤点名汇总列表
     * 
     * @param attendance 学生考勤点名汇总
     * @return 学生考勤点名汇总
     */
    @Override
    public List<Attendance> selectAttendanceList(Attendance attendance)
    {
        return attendanceMapper.selectAttendanceList(attendance);
    }

    /**
     * 新增学生考勤点名汇总
     * 
     * @param attendance 学生考勤点名汇总
     * @return 结果
     */
    @Override
    public int insertAttendance(Attendance attendance)
    {
        attendance.setCreateTime(DateUtils.getNowDate());
        return attendanceMapper.insertAttendance(attendance);
    }

    /**
     * 修改学生考勤点名汇总
     * 
     * @param attendance 学生考勤点名汇总
     * @return 结果
     */
    @Override
    public int updateAttendance(Attendance attendance)
    {
        return attendanceMapper.updateAttendance(attendance);
    }

    /**
     * 删除学生考勤点名汇总对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAttendanceByIds(String ids)
    {
        return attendanceMapper.deleteAttendanceByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生考勤点名汇总信息
     * 
     * @param id 学生考勤点名汇总ID
     * @return 结果
     */
    @Override
    public int deleteAttendanceById(Long id)
    {
        return attendanceMapper.deleteAttendanceById(id);
    }

}
