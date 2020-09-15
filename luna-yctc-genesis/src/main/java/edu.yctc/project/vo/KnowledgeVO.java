package edu.yctc.project.vo;

import edu.yctc.framework.aspectj.lang.annotation.Excel;

/**
 * @author Luna@win10
 * @date 2020/4/25 22:10
 */
public class KnowledgeVO {

    /** 课时 */
    @Excel(name = "第几次课时")
    private Long   number;

    /** 知识点描述 */
    @Excel(name = "知识点描述")
    private String content;

    /** 预计时长 */
    @Excel(name = "预计时长")
    private Double length;

    /** 课程名称 */
    @Excel(name = "课程名称")
    private String name;

    /** 开课学年 */
    @Excel(name = "开课学年")
    private String year;

    /** 开课学期 */
    @Excel(name = "开课学期")
    private Long   term;

    private String classNumber;

    public String getClassNumber() {
        return classNumber;
    }

    public void setClassNumber(String classNumber) {
        this.classNumber = classNumber;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Long getTerm() {
        return term;
    }

    public void setTerm(Long term) {
        this.term = term;
    }

    @Override
    public String toString() {
        return "KnowledgeVO{" +
            "number=" + number +
            ", content='" + content + '\'' +
            ", length=" + length +
            ", name='" + name + '\'' +
            ", year='" + year + '\'' +
            ", term=" + term +
            ", classNumber='" + classNumber + '\'' +
            '}';
    }
}
