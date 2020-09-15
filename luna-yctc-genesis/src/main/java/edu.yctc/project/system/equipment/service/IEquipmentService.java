package edu.yctc.project.system.equipment.service;

import java.util.List;

import edu.yctc.project.system.equipment.domain.Equipment;

/**
 * 设备信息Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface IEquipmentService 
{
    /**
     * 查询设备信息
     * 
     * @param id 设备信息ID
     * @return 设备信息
     */
    public Equipment selectEquipmentById(Long id);

    /**
     * 查询设备信息列表
     * 
     * @param equipment 设备信息
     * @return 设备信息集合
     */
    public List<Equipment> selectEquipmentList(Equipment equipment);

    /**
     * 新增设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    public int insertEquipment(Equipment equipment);

    /**
     * 修改设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    public int updateEquipment(Equipment equipment);

    /**
     * 批量删除设备信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteEquipmentByIds(String ids);

    /**
     * 删除设备信息信息
     * 
     * @param id 设备信息ID
     * @return 结果
     */
    public int deleteEquipmentById(Long id);
}
