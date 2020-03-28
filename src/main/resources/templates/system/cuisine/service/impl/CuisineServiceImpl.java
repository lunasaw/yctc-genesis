package com.luna.project.system.cuisine.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.luna.project.system.cuisine.mapper.CuisineMapper;
import com.luna.project.system.cuisine.domain.Cuisine;
import com.luna.project.system.cuisine.service.ICuisineService;
import com.luna.common.utils.text.Convert;

/**
 * 菜品信息Service业务层处理
 * 
 * @author luna
 * @date 2020-03-28
 */
@Service
public class CuisineServiceImpl implements ICuisineService 
{
    @Autowired
    private CuisineMapper cuisineMapper;

    /**
     * 查询菜品信息
     * 
     * @param cuisineId 菜品信息ID
     * @return 菜品信息
     */
    @Override
    public Cuisine selectCuisineById(Long cuisineId)
    {
        return cuisineMapper.selectCuisineById(cuisineId);
    }

    /**
     * 查询菜品信息列表
     * 
     * @param cuisine 菜品信息
     * @return 菜品信息
     */
    @Override
    public List<Cuisine> selectCuisineList(Cuisine cuisine)
    {
        return cuisineMapper.selectCuisineList(cuisine);
    }

    /**
     * 新增菜品信息
     * 
     * @param cuisine 菜品信息
     * @return 结果
     */
    @Override
    public int insertCuisine(Cuisine cuisine)
    {
        return cuisineMapper.insertCuisine(cuisine);
    }

    /**
     * 修改菜品信息
     * 
     * @param cuisine 菜品信息
     * @return 结果
     */
    @Override
    public int updateCuisine(Cuisine cuisine)
    {
        return cuisineMapper.updateCuisine(cuisine);
    }

    /**
     * 删除菜品信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteCuisineByIds(String ids)
    {
        return cuisineMapper.deleteCuisineByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除菜品信息信息
     * 
     * @param cuisineId 菜品信息ID
     * @return 结果
     */
    @Override
    public int deleteCuisineById(Long cuisineId)
    {
        return cuisineMapper.deleteCuisineById(cuisineId);
    }
}
