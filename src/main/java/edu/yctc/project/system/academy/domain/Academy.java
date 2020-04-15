package edu.yctc.project.system.academy.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import java.util.Date;

/**
 * 学院对象 sys_academy
 * 
 * @author luna
 * @date 2020-04-15
 */
public class Academy extends BaseEntity {
    private static final long         serialVersionUID = 1L;

    /** 编号 */
    private Long                      id;

    /**
     * 学院名称
     */
    @Excel(name = "学院名称 ")
    private String academy;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date                      modifyTime;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setAcademy(String academy) {
        this.academy = academy;
    }

    public String getAcademy() {
        return academy;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("academy", getAcademy())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
