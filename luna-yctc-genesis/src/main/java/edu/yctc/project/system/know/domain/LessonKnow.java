package edu.yctc.project.system.know.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.lesson.domain.Lesson;

/**
 * 课时知识点信息对象 sys_lesson_know
 * 
 * @author yctc
 * @date 2020-04-18
 */
public class LessonKnow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** $column.columnComment */
    private Long id;

    /** 课程编号 */
    @Excel(name = "课程编号")
    private Long lessonId;

    /** 知识点编号 */
    @Excel(name = "知识点编号")
    private Long knowledgeId;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private Knowledge knowledge;

    private Lesson lesson;

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
    public void setLessonId(Long lessonId) 
    {
        this.lessonId = lessonId;
    }

    public Long getLessonId() 
    {
        return lessonId;
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
            .append("lessonId", getLessonId())
            .append("knowledgeId", getKnowledgeId())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
