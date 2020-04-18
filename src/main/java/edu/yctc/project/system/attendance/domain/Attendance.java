package edu.yctc.project.system.attendance.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.user.domain.User;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生考勤点名汇总对象 sys_attendance
 * 
 * @author yctc
 * @date 2020-04-16
 */
public class Attendance extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 课时编号 */
    @Excel(name = "课时编号")
    private Long lessonId;

    /** 出勤情况 */
    @Excel(name = "出勤情况")
    private Long attendState;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private Lesson lesson;

    private User user;

    private Infost infost;

	public Infost getInfost() {
		if (infost == null)
		{
			infost = new Infost();
		}
		return infost;
	}

	public void setInfost(Infost infost) {
		this.infost = infost;
	}

	public User getUser() {
		if (user == null)
		{
			user = new User();
		}
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Lesson getLesson() {
		if (lesson == null)
		{
			lesson = new Lesson();
		}
		return lesson;
	}

	public void setLesson(Lesson lesson) {
		this.lesson = lesson;
	}

	public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setLessonId(Long lessonId) 
    {
        this.lessonId = lessonId;
    }

    public Long getLessonId() 
    {
        return lessonId;
    }
    public void setAttendState(Long attendState) 
    {
        this.attendState = attendState;
    }

    public Long getAttendState() 
    {
        return attendState;
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
            .append("userId", getUserId())
            .append("lessonId", getLessonId())
            .append("attendState", getAttendState())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
