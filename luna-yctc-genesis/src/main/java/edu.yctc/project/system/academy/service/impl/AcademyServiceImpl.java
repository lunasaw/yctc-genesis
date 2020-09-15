package edu.yctc.project.system.academy.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.academy.domain.Academy;
import edu.yctc.project.system.academy.mapper.AcademyMapper;
import edu.yctc.project.system.academy.service.IAcademyService;

/**
 * 学院Service业务层处理
 * 
 * @author luna
 * @date 2020-04-15
 */
@Service
public class AcademyServiceImpl implements IAcademyService
{
    @Autowired
    private AcademyMapper academyMapper;

    /**
     * 查询学院
     * 
     * @param id 学院ID
     * @return 学院
     */
    @Override
    public Academy selectAcademyById(Long id)
    {
        return academyMapper.selectAcademyById(id);
    }

    /**
     * 查询学院列表
     * 
     * @param academy 学院
     * @return 学院
     */
    @Override
    public List<Academy> selectAcademyList(Academy academy)
    {
        return academyMapper.selectAcademyList(academy);
    }

    /**
     * 新增学院
     * 
     * @param academy 学院
     * @return 结果
     */
    @Override
    public int insertAcademy(Academy academy)
    {
        academy.setCreateTime(DateUtils.getNowDate());
        return academyMapper.insertAcademy(academy);
    }

    /**
     * 修改学院
     * 
     * @param academy 学院
     * @return 结果
     */
    @Override
    public int updateAcademy(Academy academy)
    {
        return academyMapper.updateAcademy(academy);
    }

    /**
     * 删除学院对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAcademyByIds(String ids)
    {
        return academyMapper.deleteAcademyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学院信息
     * 
     * @param id 学院ID
     * @return 结果
     */
    @Override
    public int deleteAcademyById(Long id)
    {
        return academyMapper.deleteAcademyById(id);
    }
}
