package edu.yctc.project.system.knowledgeStudentState.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.lesson.domain.Lesson;
import edu.yctc.project.system.user.domain.User;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 知识点和学生状态关联对象 sys_knowledge_student_state
 * 
 * @author yctc
 * @date 2020-04-19
 */
public class KnowledgeStudentState extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 用户id */
    @Excel(name = "用户id")
    private Long userId;

    /** 课程id */
    @Excel(name = "课程id")
    private Long lessonId;

    /** 状态 */
    @Excel(name = "状态")
    private Integer state;

    /** 开始扫描时间 */
    @Excel(name = "开始扫描时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scanStartTime;

    /** 结束扫描时间 */
    @Excel(name = "结束扫描时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date scanEndTime;

    /** 知识点id */
    @Excel(name = "知识点id")
    private Long knowledgeId;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private User user;

    private Lesson lesson;

    private Knowledge knowledge;

    private Infost infost;

	public Infost getInfost() {
		if (infost == null) {
			infost = new Infost();
		}
		return infost;
	}

	public void setInfost(Infost infost) {
		this.infost = infost;
	}

	public User getUser() {
        if (user == null) {
            user = new User();
        }
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Lesson getLesson() {
        if (lesson == null) {
            lesson = new Lesson();
        }
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

    public Knowledge getKnowledge() {
        if (knowledge == null) {
            knowledge = new Knowledge();
        }
        return knowledge;
    }

    public void setKnowledge(Knowledge knowledge) {
        this.knowledge = knowledge;
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
    public void setState(Integer state) 
    {
        this.state = state;
    }

    public Integer getState() 
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
    public void setKnowledgeId(Long knowledgeId) 
    {
        this.knowledgeId = knowledgeId;
    }

    public Long getKnowledgeId() 
    {
        return knowledgeId;
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
            .append("knowledgeId", getKnowledgeId())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
