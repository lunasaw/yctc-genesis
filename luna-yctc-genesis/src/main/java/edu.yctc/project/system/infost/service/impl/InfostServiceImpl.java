package edu.yctc.project.system.infost.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.infost.domain.Infost;
import edu.yctc.project.system.infost.mapper.InfostMapper;
import edu.yctc.project.system.infost.service.IInfostService;

/**
 * 学生/老师档案Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-17
 */
@Service
public class InfostServiceImpl implements IInfostService 
{
    @Autowired
    private InfostMapper infostMapper;

    /**
     * 查询学生/老师档案
     * 
     * @param id 学生/老师档案ID
     * @return 学生/老师档案
     */
    @Override
    public Infost selectInfostById(Long id)
    {
        return infostMapper.selectInfostById(id);
    }

    /**
     * 查询学生/老师档案列表
     * 
     * @param infost 学生/老师档案
     * @return 学生/老师档案
     */
    @Override
    public List<Infost> selectInfostList(Infost infost)
    {
        return infostMapper.selectInfostList(infost);
    }

    /**
     * 新增学生/老师档案
     * 
     * @param infost 学生/老师档案
     * @return 结果
     */
    @Override
    public int insertInfost(Infost infost)
    {
        infost.setCreateTime(DateUtils.getNowDate());
        return infostMapper.insertInfost(infost);
    }

    /**
     * 修改学生/老师档案
     * 
     * @param infost 学生/老师档案
     * @return 结果
     */
    @Override
    public int updateInfost(Infost infost)
    {
        return infostMapper.updateInfost(infost);
    }

    /**
     * 删除学生/老师档案对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteInfostByIds(String ids)
    {
        return infostMapper.deleteInfostByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除学生/老师档案信息
     * 
     * @param id 学生/老师档案ID
     * @return 结果
     */
    @Override
    public int deleteInfostById(Long id)
    {
        return infostMapper.deleteInfostById(id);
    }
}
