package edu.yctc.project.system.floor.service;

import java.util.List;
import edu.yctc.project.system.floor.domain.BuildingFloor;

/**
 * 楼层信息Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface IBuildingFloorService 
{
    /**
     * 查询楼层信息
     * 
     * @param id 楼层信息ID
     * @return 楼层信息
     */
    public BuildingFloor selectBuildingFloorById(Long id);

    /**
     * 查询楼层信息列表
     * 
     * @param buildingFloor 楼层信息
     * @return 楼层信息集合
     */
    public List<BuildingFloor> selectBuildingFloorList(BuildingFloor buildingFloor);

    /**
     * 新增楼层信息
     * 
     * @param buildingFloor 楼层信息
     * @return 结果
     */
    public int insertBuildingFloor(BuildingFloor buildingFloor);

    /**
     * 修改楼层信息
     * 
     * @param buildingFloor 楼层信息
     * @return 结果
     */
    public int updateBuildingFloor(BuildingFloor buildingFloor);

    /**
     * 批量删除楼层信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBuildingFloorByIds(String ids);

    /**
     * 删除楼层信息信息
     * 
     * @param id 楼层信息ID
     * @return 结果
     */
    public int deleteBuildingFloorById(Long id);
}
