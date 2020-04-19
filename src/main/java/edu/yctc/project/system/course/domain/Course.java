package edu.yctc.project.system.course.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.classes.domain.Classes;
import edu.yctc.project.system.dept.domain.Dept;
import edu.yctc.project.system.tea.domain.LessonTea;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

/**
 * 课程信息对象 sys_course
 * 
 * @author yctc
 * @date 2020-04-16
 */
public class Course extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 课程id */
    private Long id;

    /** 课程号 */
    @Excel(name = "课程号")
    private String number;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 课时(第几课时) */
    @Excel(name = "课时(第几课时)")
    private Long lessonSum;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String year;

    /** 开课学期 */
    @Excel(name = "开课学期")
    private Long term;

    /** 开课专业 */
    @Excel(name = "开课专业")
    private Long deptId;

    /** 开课班级 */
    @Excel(name = "开课班级")
    private String classesId;

    @Excel(name = "开课班级")
    private Classes classes;

	private Dept dept;

	public Classes getClasses() {
		if (classes == null) {
			classes = new Classes();
		}
		return classes;
	}

	public void setClasses(Classes classes) {
		this.classes = classes;
	}

	public Dept getDept() {
		if (dept == null) {
			dept = new Dept();
		}
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	private List<Classes> classesList;

	public List<Classes> getClassesList() {
		if (classesList == null) {
			classesList = new ArrayList<Classes>();
		}
		return classesList;
	}

	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}

	public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setLessonSum(Long lessonSum) 
    {
        this.lessonSum = lessonSum;
    }

    public Long getLessonSum() 
    {
        return lessonSum;
    }
    public void setYear(String year) 
    {
        this.year = year;
    }

    public String getYear() 
    {
        return year;
    }
    public void setTerm(Long term) 
    {
        this.term = term;
    }

    public Long getTerm() 
    {
        return term;
    }
    public void setDeptId(Long deptId) 
    {
        this.deptId = deptId;
    }

    public Long getDeptId() 
    {
        return deptId;
    }
    public void setClassesId(String classesId) 
    {
        this.classesId = classesId;
    }

    public String getClassesId() 
    {
        return classesId;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("number", getNumber())
            .append("name", getName())
            .append("lessonSum", getLessonSum())
            .append("year", getYear())
            .append("term", getTerm())
            .append("deptId", getDeptId())
            .append("classesId", getClassesId())
            .toString();
    }
}
