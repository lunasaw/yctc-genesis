package edu.yctc.project.system.stu.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.course.domain.Course;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.tea.domain.LessonTea;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * 学生课程信息对象 sys_course_stu
 * 
 * @author yctc
 * @date 2020-04-18
 */
public class CourseStu extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户编号 */
    @Excel(name = "用户编号")
    private Long userId;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long courseId;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

	private Infost infost;

	private LessonTea lessonTea;

	private String stuname;

	public String getStuname() {
		return stuname;
	}

	public void setStuname(String stuname) {
		this.stuname = stuname;
	}

	public LessonTea getLessonTea() {
		if (lessonTea == null) {
			lessonTea = new LessonTea();
		}
		return lessonTea;
	}

	public void setLessonTea(LessonTea lessonTea) {
		this.lessonTea = lessonTea;
	}

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
    public void setCourseId(Long courseId) 
    {
        this.courseId = courseId;
    }

    public Long getCourseId() 
    {
        return courseId;
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
            .append("courseId", getCourseId())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
