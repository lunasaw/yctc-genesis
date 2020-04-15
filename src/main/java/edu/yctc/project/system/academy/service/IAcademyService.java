package edu.yctc.project.system.academy.service;

import java.util.List;
import edu.yctc.project.system.academy.domain.Academy;

/**
 * 学院Service接口
 * 
 * @author luna
 * @date 2020-04-15
 */
public interface IAcademyService 
{
    /**
     * 查询学院
     * 
     * @param id 学院ID
     * @return 学院
     */
    public Academy selectAcademyById(Long id);

    /**
     * 查询学院列表
     * 
     * @param academy 学院
     * @return 学院集合
     */
    public List<Academy> selectAcademyList(Academy academy);

    /**
     * 新增学院
     * 
     * @param academy 学院
     * @return 结果
     */
    public int insertAcademy(Academy academy);

    /**
     * 修改学院
     * 
     * @param academy 学院
     * @return 结果
     */
    public int updateAcademy(Academy academy);

    /**
     * 批量删除学院
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAcademyByIds(String ids);

    /**
     * 删除学院信息
     * 
     * @param id 学院ID
     * @return 结果
     */
    public int deleteAcademyById(Long id);
}
