package edu.yctc.project.system.knowledge.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.lesson.domain.Lesson;

/**
 * 知识点概览对象 sys_knowledge
 * 
 * @author yctc
 * @date 2020-04-21
 */
public class Knowledge extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 知识点描述 */
    @Excel(name = "知识点描述")
    private String content;

    /** 预计时长 */
    @Excel(name = "预计时长")
    private Double length;

    /** 课时 */
    @Excel(name = "课时")
    private Long lessonId;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private Lesson            lesson;

    public Lesson getLesson() {
        if (lesson == null) {
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
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setLength(Double length) 
    {
        this.length = length;
    }

    public Double getLength() 
    {
        return length;
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
            .append("content", getContent())
            .append("length", getLength())
            .append("lessonId", getLessonId())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
