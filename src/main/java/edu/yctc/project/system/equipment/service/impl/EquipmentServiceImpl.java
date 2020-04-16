package edu.yctc.project.system.equipment.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.equipment.mapper.EquipmentMapper;
import edu.yctc.project.system.equipment.domain.Equipment;
import edu.yctc.project.system.equipment.service.IEquipmentService;
import edu.yctc.common.utils.text.Convert;

/**
 * 设备信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class EquipmentServiceImpl implements IEquipmentService 
{
    @Autowired
    private EquipmentMapper equipmentMapper;

    /**
     * 查询设备信息
     * 
     * @param id 设备信息ID
     * @return 设备信息
     */
    @Override
    public Equipment selectEquipmentById(Long id)
    {
        return equipmentMapper.selectEquipmentById(id);
    }

    /**
     * 查询设备信息列表
     * 
     * @param equipment 设备信息
     * @return 设备信息
     */
    @Override
    public List<Equipment> selectEquipmentList(Equipment equipment)
    {
        return equipmentMapper.selectEquipmentList(equipment);
    }

    /**
     * 新增设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    @Override
    public int insertEquipment(Equipment equipment)
    {
        equipment.setCreateTime(DateUtils.getNowDate());
        return equipmentMapper.insertEquipment(equipment);
    }

    /**
     * 修改设备信息
     * 
     * @param equipment 设备信息
     * @return 结果
     */
    @Override
    public int updateEquipment(Equipment equipment)
    {
        return equipmentMapper.updateEquipment(equipment);
    }

    /**
     * 删除设备信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentByIds(String ids)
    {
        return equipmentMapper.deleteEquipmentByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除设备信息信息
     * 
     * @param id 设备信息ID
     * @return 结果
     */
    @Override
    public int deleteEquipmentById(Long id)
    {
        return equipmentMapper.deleteEquipmentById(id);
    }
}
