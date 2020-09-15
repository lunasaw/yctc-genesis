package edu.yctc.project.system.classroom.domain;

import java.util.Date;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import edu.yctc.project.system.equipment.domain.Equipment;
import edu.yctc.project.system.floor.domain.BuildingFloor;

/**
 * 教室对象 sys_classroom
 * 
 * @author luna
 * @date 2020-04-15
 */
public class Classroom extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 教室id */
    private Long id;

    /** 楼层号id */
    @Excel(name = "楼层号id")
    private Long buildingFloorId;

    /** 楼层号 */
    @Excel(name = "教室号")
    private String number;

    /** 设备id */
    @Excel(name = "设备id")
    private String equipmentId;

    /** 修改时间 */
    @Excel(name = "修改时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date modifyTime;

    private BuildingFloor buildingFloor;

	private Equipment equipment;

	public Equipment getEquipment() {
		if (equipment == null)
		{
			equipment = new Equipment();
		}
		return equipment;
	}

	public void setEquipment(Equipment equipment) {
		this.equipment = equipment;
	}

	public BuildingFloor getBuildingFloor() {
		if (buildingFloor == null)
		{
			buildingFloor = new BuildingFloor();
		}
		return buildingFloor;
	}

	public void setBuildingFloor(BuildingFloor buildingFloor) {
		this.buildingFloor = buildingFloor;
	}

	public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setBuildingFloorId(Long buildingFloorId) 
    {
        this.buildingFloorId = buildingFloorId;
    }

    public Long getBuildingFloorId() 
    {
        return buildingFloorId;
    }
    public void setNumber(String number) 
    {
        this.number = number;
    }

    public String getNumber() 
    {
        return number;
    }
    public void setEquipmentId(String equipmentId) 
    {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentId() 
    {
        return equipmentId;
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
            .append("buildingFloorId", getBuildingFloorId())
            .append("number", getNumber())
            .append("equipmentId", getEquipmentId())
            .append("createTime", getCreateTime())
            .append("modifyTime", getModifyTime())
            .toString();
    }
}
