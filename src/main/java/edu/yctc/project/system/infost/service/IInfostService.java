package edu.yctc.project.system.infost.service;

import java.util.List;
import edu.yctc.project.system.infost.domain.Infost;

/**
 * 学生/老师档案Service接口
 * 
 * @author yctc
 * @date 2020-04-17
 */
public interface IInfostService 
{
    /**
     * 查询学生/老师档案
     * 
     * @param id 学生/老师档案ID
     * @return 学生/老师档案
     */
    public Infost selectInfostById(Long id);

    /**
     * 查询学生/老师档案列表
     * 
     * @param infost 学生/老师档案
     * @return 学生/老师档案集合
     */
    public List<Infost> selectInfostList(Infost infost);

    /**
     * 新增学生/老师档案
     * 
     * @param infost 学生/老师档案
     * @return 结果
     */
    public int insertInfost(Infost infost);

    /**
     * 修改学生/老师档案
     * 
     * @param infost 学生/老师档案
     * @return 结果
     */
    public int updateInfost(Infost infost);

    /**
     * 批量删除学生/老师档案
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfostByIds(String ids);

    /**
     * 删除学生/老师档案信息
     * 
     * @param id 学生/老师档案ID
     * @return 结果
     */
    public int deleteInfostById(Long id);
}
