package edu.yctc.project.system.floor.service.impl;

import java.util.List;
import edu.yctc.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.yctc.project.system.floor.mapper.BuildingFloorMapper;
import edu.yctc.project.system.floor.domain.BuildingFloor;
import edu.yctc.project.system.floor.service.IBuildingFloorService;
import edu.yctc.common.utils.text.Convert;

/**
 * 楼层信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class BuildingFloorServiceImpl implements IBuildingFloorService 
{
    @Autowired
    private BuildingFloorMapper buildingFloorMapper;

    /**
     * 查询楼层信息
     * 
     * @param id 楼层信息ID
     * @return 楼层信息
     */
    @Override
    public BuildingFloor selectBuildingFloorById(Long id)
    {
        return buildingFloorMapper.selectBuildingFloorById(id);
    }

    /**
     * 查询楼层信息列表
     * 
     * @param buildingFloor 楼层信息
     * @return 楼层信息
     */
    @Override
    public List<BuildingFloor> selectBuildingFloorList(BuildingFloor buildingFloor)
    {
        return buildingFloorMapper.selectBuildingFloorList(buildingFloor);
    }

    /**
     * 新增楼层信息
     * 
     * @param buildingFloor 楼层信息
     * @return 结果
     */
    @Override
    public int insertBuildingFloor(BuildingFloor buildingFloor)
    {
        buildingFloor.setCreateTime(DateUtils.getNowDate());
        return buildingFloorMapper.insertBuildingFloor(buildingFloor);
    }

    /**
     * 修改楼层信息
     * 
     * @param buildingFloor 楼层信息
     * @return 结果
     */
    @Override
    public int updateBuildingFloor(BuildingFloor buildingFloor)
    {
        return buildingFloorMapper.updateBuildingFloor(buildingFloor);
    }

    /**
     * 删除楼层信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBuildingFloorByIds(String ids)
    {
        return buildingFloorMapper.deleteBuildingFloorByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除楼层信息信息
     * 
     * @param id 楼层信息ID
     * @return 结果
     */
    @Override
    public int deleteBuildingFloorById(Long id)
    {
        return buildingFloorMapper.deleteBuildingFloorById(id);
    }
}
