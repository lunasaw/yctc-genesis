package edu.yctc.project.system.floor.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;

/**
 * 楼层信息对象 sys_building_floor
 *
 * @author yctc
 * @date 2020-04-22
 */
public class BuildingFloor extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** 编号 */
	private Long id;

	/** 楼号 */
	@Excel(name = "楼号")
	private String building;

	/** 楼层 */
	@Excel(name = "楼层")
	private String floor;

	/** 修改时间 */
	@Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
	private Date modifyTime;

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId()
	{
		return id;
	}
	public void setBuilding(String building)
	{
		this.building = building;
	}

	public String getBuilding()
	{
		return building;
	}
	public void setFloor(String floor)
	{
		this.floor = floor;
	}

	public String getFloor()
	{
		return floor;
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
				.append("building", getBuilding())
				.append("floor", getFloor())
				.append("createTime", getCreateTime())
				.append("modifyTime", getModifyTime())
				.toString();
	}
}
