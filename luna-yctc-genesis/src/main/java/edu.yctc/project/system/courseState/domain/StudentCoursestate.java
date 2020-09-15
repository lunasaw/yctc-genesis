package edu.yctc.project.system.courseState.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.lesson.domain.Lesson;

/**
 * 学生上课状态信息汇总对象 sys_student_courseState
 * 
 * @author yctc
 * @date 2020-04-17
 */
public class StudentCoursestate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 课时id */
    @Excel(name = "课时id")
    private Long lessonId;

    /** 学生上课状态 */
    @Excel(name = "学生上课状态")
    private Long state;

    /** 扫描开始时间 */
    @Excel(name = "扫描开始时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scanStartTime;

    /** 扫描结束时间 */
    @Excel(name = "扫描结束时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scanEndTime;

    /** 人脸标识 */
    @Excel(name = "人脸标识")
    private String faceToken;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private Lesson lesson;

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
    public void setState(Long state) 
    {
        this.state = state;
    }

    public Long getState() 
    {
        return state;
    }
    public void setScanStartTime(Date scanStartTime) 
    {
        this.scanStartTime = scanStartTime;
    }

    public Date getScanStartTime() 
    {
        return scanStartTime;
    }
    public void setScanEndTime(Date scanEndTime) 
    {
        this.scanEndTime = scanEndTime;
    }

    public Date getScanEndTime() 
    {
        return scanEndTime;
    }
    public void setFaceToken(String faceToken) 
    {
        this.faceToken = faceToken;
    }

    public String getFaceToken() 
    {
        return faceToken;
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
            .append("state", getState())
            .append("scanStartTime", getScanStartTime())
            .append("scanEndTime", getScanEndTime())
            .append("faceToken", getFaceToken())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
