package edu.yctc.project.system.classes.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.classes.domain.Classes;
import edu.yctc.project.system.classes.mapper.ClassesMapper;
import edu.yctc.project.system.classes.service.IClassesService;

/**
 * 班级信息Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-16
 */
@Service
public class ClassesServiceImpl implements IClassesService 
{
    @Autowired
    private ClassesMapper classesMapper;

    /**
     * 查询班级信息
     * 
     * @param id 班级信息ID
     * @return 班级信息
     */
    @Override
    public Classes selectClassesById(Long id)
    {
        return classesMapper.selectClassesById(id);
    }

    /**
     * 查询班级信息列表
     * 
     * @param classes 班级信息
     * @return 班级信息
     */
    @Override
    public List<Classes> selectClassesList(Classes classes)
    {
        return classesMapper.selectClassesList(classes);
    }

    /**
     * 新增班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int insertClasses(Classes classes)
    {
        return classesMapper.insertClasses(classes);
    }

    /**
     * 修改班级信息
     * 
     * @param classes 班级信息
     * @return 结果
     */
    @Override
    public int updateClasses(Classes classes)
    {
        return classesMapper.updateClasses(classes);
    }

    /**
     * 删除班级信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteClassesByIds(String ids)
    {
        return classesMapper.deleteClassesByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除班级信息信息
     * 
     * @param id 班级信息ID
     * @return 结果
     */
    @Override
    public int deleteClassesById(Long id)
    {
        return classesMapper.deleteClassesById(id);
    }
}
