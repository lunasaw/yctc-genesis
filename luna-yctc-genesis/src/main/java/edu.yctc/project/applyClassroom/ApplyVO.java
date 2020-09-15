package edu.yctc.project.applyClassroom;

import java.util.Date;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;

/**
 * @author Luna@win10
 * @date 2020/4/21 16:40
 */
public class ApplyVO extends BaseEntity {

	/** 楼号 */
	@Excel(name = "楼号")
	private String building;

	/** 楼层 */
	private String floor;

	/** 楼层号 */
	@Excel(name = "教室号")
	private String number;

	/** 上课时间 */
	@Excel(name = "上课时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date begin;

	/** 下课时间 */
	@Excel(name = "下课时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date end;

	/** 课程名称 */
	@Excel(name = "课程名称")
	private String name;

	/** 开课学年 */
	@Excel(name = "开课学年")
	private String year;

	/** 开课学期 */
	@Excel(name = "开课学期")
	private Long term;

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

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getFloor() {
		return floor;
	}

	public void setFloor(String floor) {
		this.floor = floor;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getBegin() {
		return begin;
	}

	public void setBegin(Date begin) {
		this.begin = begin;
	}

	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ApplyVO{" +
				"building='" + building + '\'' +
				", floor='" + floor + '\'' +
				", number='" + number + '\'' +
				", begin=" + begin +
				", end=" + end +
				", name='" + name + '\'' +
				", year='" + year + '\'' +
				", term=" + term +
				'}';
	}
}
