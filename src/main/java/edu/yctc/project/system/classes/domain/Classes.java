package edu.yctc.project.system.classes.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.academy.domain.Academy;
import edu.yctc.project.system.dept.domain.Dept;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 班级信息对象 sys_classes
 * 
 * @author yctc
 * @date 2020-04-16
 */
public class Classes extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private Long id;

    /** 学院编号 */
    @Excel(name = "学院编号")
    private Long academyId;

    /** 专业编号 */
    @Excel(name = "专业编号")
    private Long deptId;

    /** 班级编号 */
    @Excel(name = "班级编号")
    private String number;

    private Academy academy;

	private Dept dept;
	public Academy getAcademy()
	{
		if (academy == null)
		{
			academy = new Academy();
		}
		return academy;
	}

	public void setacademy(Academy academy)
	{
		this.academy = academy;
	}

	public Dept getDept()
	{
		if (dept == null)
		{
			dept = new Dept();
		}
		return dept;
	}

	public void setDept(Dept dept)
	{
		this.dept = dept;
	}

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAcademyId(Long academyId)
    {
        this.academyId = academyId;
    }

    public Long getAcademyId()
    {
        return academyId;
    }
    public void setDeptId(Long deptId)
    {
        this.deptId = deptId;
    }

    public Long getDeptId()
    {
        return deptId;
    }
    public void setNumber(String number)
    {
        this.number = number;
    }

    public String getNumber()
    {
        return number;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("academyId", getAcademyId())
            .append("deptId", getDeptId())
            .append("number", getNumber())
            .toString();
    }
}
