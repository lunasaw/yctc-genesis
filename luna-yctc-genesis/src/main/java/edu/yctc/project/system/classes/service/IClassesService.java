package edu.yctc.project.system.classes.service;

import java.util.List;

import edu.yctc.project.system.classes.domain.Classes;

/**
 * 班级信息Service接口
 * 
 * @author yctc
 * @date 2020-04-16
 */
public interface IClassesService 
{
    /**
     * 查询班级信息
     * 
     * @param id 班级信息ID
     * @return 班级信息
     */
    public Classes selectClassesById(Long id);

    /**
     * 查询班级信息列表
     * 
     * @param classes 班级信息
     * @return 班级信息集合
     */
    public List<Classes> selectClassesList(Classes classes);

    /**
     * 新增班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int insertClasses(Classes classes);

    /**
     * 修改班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    public int updateClasses(Classes classes);

    /**
     * 批量删除班级信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteClassesByIds(String ids);

    /**
     * 删除班级信息信息
     * 
     * @param id 班级信息ID
     * @return 结果
     */
    public int deleteClassesById(Long id);
}
