package edu.yctc.project.system.cuisine.mapper;

import java.util.List;
import edu.yctc.project.system.cuisine.domain.Cuisine;

/**
 * 菜品信息Mapper接口
 * 
 * @author luna
 * @date 2020-03-28
 */
public interface CuisineMapper 
{
    /**
     * 查询菜品信息
     * 
     * @param cuisineId 菜品信息ID
     * @return 菜品信息
     */
    public Cuisine selectCuisineById(Long cuisineId);

    /**
     * 查询菜品信息列表
     * 
     * @param cuisine 菜品信息
     * @return 菜品信息集合
     */
    public List<Cuisine> selectCuisineList(Cuisine cuisine);

    /**
     * 新增菜品信息
     * 
     * @param cuisine 菜品信息
     * @return 结果
     */
    public int insertCuisine(Cuisine cuisine);

    /**
     * 修改菜品信息
     * 
     * @param cuisine 菜品信息
     * @return 结果
     */
    public int updateCuisine(Cuisine cuisine);

    /**
     * 删除菜品信息
     * 
     * @param cuisineId 菜品信息ID
     * @return 结果
     */
    public int deleteCuisineById(Long cuisineId);

    /**
     * 批量删除菜品信息
     * 
     * @param cuisineIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCuisineByIds(String[] cuisineIds);
}
