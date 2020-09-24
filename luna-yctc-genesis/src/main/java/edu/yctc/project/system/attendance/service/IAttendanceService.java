package edu.yctc.project.system.attendance.service;

import java.util.List;

import edu.yctc.project.system.attendance.domain.Attendance;

/**
 * 学生考勤点名汇总Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface IAttendanceService 
{
    /**
     * 查询学生考勤点名汇总
     * 
     * @param id 学生考勤点名汇总ID
     * @return 学生考勤点名汇总
     */
    public Attendance selectAttendanceById(Long id);

    /**
     * 查询学生考勤点名汇总列表
     * 
     * @param attendance 学生考勤点名汇总
     * @return 学生考勤点名汇总集合
     */
    public List<Attendance> selectAttendanceList(Attendance attendance);

    /**
     * 新增学生考勤点名汇总
     * 
     * @param attendance 学生考勤点名汇总
     * @return 结果
     */
    public int insertAttendance(Attendance attendance);

    /**
     * 修改学生考勤点名汇总
     * 
     * @param attendance 学生考勤点名汇总
     * @return 结果
     */
    public int updateAttendance(Attendance attendance);

    /**
     * 批量删除学生考勤点名汇总
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAttendanceByIds(String ids);

    /**
     * 删除学生考勤点名汇总信息
     * 
     * @param id 学生考勤点名汇总ID
     * @return 结果
     */
    public int deleteAttendanceById(Long id);
}
