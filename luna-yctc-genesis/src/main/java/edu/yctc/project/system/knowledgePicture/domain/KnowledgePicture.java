package edu.yctc.project.system.knowledgePicture.domain;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.knowledge.domain.Knowledge;

/**
 * 图片和知识点关联对象 sys_knowledge_picture
 * 
 * @author yctc
 * @date 2020-04-19
 */
public class KnowledgePicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 识别到的照片 */
    @Excel(name = "识别到的照片")
    private String picture;

    /** 知识点编号 */
    @Excel(name = "知识点编号")
    private Long knowledgeid;

    private Knowledge knowledge;

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
    public void setPicture(String picture) 
    {
        this.picture = picture;
    }

    public String getPicture() 
    {
        return picture;
    }
    public void setKnowledgeid(Long knowledgeid) 
    {
        this.knowledgeid = knowledgeid;
    }

    public Long getKnowledgeid() 
    {
        return knowledgeid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("picture", getPicture())
            .append("knowledgeid", getKnowledgeid())
            .toString();
    }
}
