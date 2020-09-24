package edu.yctc.project.system.tea.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.lesson.domain.Lesson;

/**
 * 老师授课信息对象 sys_lesson_tea
 * 
 * @author yctc
 * @date 2020-04-18
 */
public class LessonTea extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 老师编号 */
    @Excel(name = "老师编号")
    private Long userId;

    /** 授课课时编号 */
    @Excel(name = "授课课时编号")
    private Long lessonId;

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
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
