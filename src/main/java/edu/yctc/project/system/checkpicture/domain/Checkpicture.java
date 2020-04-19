package edu.yctc.project.system.checkpicture.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledgePicture.domain.KnowledgePicture;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * 检测图片对象 sys_checkpicture
 * 
 * @author yctc
 * @date 2020-04-19
 */
public class Checkpicture extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Integer id;

    /** 图片编号 */
    @Excel(name = "图片编号")
    private Long pictureid;

    /** 状态标记 */
    @Excel(name = "状态标记")
    private Long intflag;

    /** 知识点编号 */
    @Excel(name = "知识点编号")
    private Long knowledgeid;

    private KnowledgePicture knowledgePicture;

    private Knowledge knowledge;

    public KnowledgePicture getKnowledgePicture() {
        if (knowledgePicture == null) {
            knowledgePicture = new KnowledgePicture();
        }
        return knowledgePicture;
    }

    public void setKnowledgePicture(KnowledgePicture knowledgePicture) {
        this.knowledgePicture = knowledgePicture;
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

    public void setId(Integer id)
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setPictureid(Long pictureid) 
    {
        this.pictureid = pictureid;
    }

    public Long getPictureid() 
    {
        return pictureid;
    }
    public void setIntflag(Long intflag) 
    {
        this.intflag = intflag;
    }

    public Long getIntflag() 
    {
        return intflag;
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
            .append("pictureid", getPictureid())
            .append("intflag", getIntflag())
            .append("knowledgeid", getKnowledgeid())
            .toString();
    }
}
