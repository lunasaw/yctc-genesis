package edu.yctc.project.system.lesson.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.classroom.domain.Classroom;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.equipment.domain.Equipment;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 课时信息对象 sys_lesson
 * 
 * @author yctc
 * @date 2020-04-16
 */
public class Lesson extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 课时 */
    @Excel(name = "课时")
    private Long number;

    /** 教室 */
    @Excel(name = "教室")
    private Long classroomId;

    /** 上课时间 */
    @Excel(name = "上课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date begin;

    /** 下课时间 */
    @Excel(name = "下课时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date end;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private Classroom classroom;

    private Course course;

	public Course getCourse() {
		if (course == null)
		{
			course = new Course();
		}
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Classroom getClassroom() {
		if (classroom == null)
		{
			classroom = new Classroom();
		}
		return classroom;
	}

	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
    }
    public void setNumber(Long number) 
    {
        this.number = number;
    }

    public Long getNumber() 
    {
        return number;
    }
    public void setClassroomId(Long classroomId) 
    {
        this.classroomId = classroomId;
    }

    public Long getClassroomId() 
    {
        return classroomId;
    }
    public void setBegin(Date begin) 
    {
        this.begin = begin;
    }

    public Date getBegin() 
    {
        return begin;
    }
    public void setEnd(Date end) 
    {
        this.end = end;
    }

    public Date getEnd() 
    {
        return end;
    }
    public void setModifyTime(Date modifyTime) 
    {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() 
    {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("courseId", getCourseId())
            .append("number", getNumber())
            .append("classroomId", getClassroomId())
            .append("begin", getBegin())
            .append("end", getEnd())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
