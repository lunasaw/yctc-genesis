package edu.yctc.project.system.userNews.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.userNews.domain.UserNews;
import edu.yctc.project.system.userNews.mapper.UserNewsMapper;
import edu.yctc.project.system.userNews.service.IUserNewsService;

/**
 * 用户和通知关联Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-19
 */
@Service
public class UserNewsServiceImpl implements IUserNewsService
{
    @Autowired
    private UserNewsMapper userNewsMapper;

    /**
     * 查询用户和通知关联
     * 
     * @param id 用户和通知关联ID
     * @return 用户和通知关联
     */
    @Override
    public UserNews selectUserNewsById(Long id)
    {
        return userNewsMapper.selectUserNewsById(id);
    }

    /**
     * 查询用户和通知关联列表
     * 
     * @param userNews 用户和通知关联
     * @return 用户和通知关联
     */
    @Override
    public List<UserNews> selectUserNewsList(UserNews userNews)
    {
        return userNewsMapper.selectUserNewsList(userNews);
    }

    /**
     * 新增用户和通知关联
     * 
     * @param userNews 用户和通知关联
     * @return 结果
     */
    @Override
    public int insertUserNews(UserNews userNews)
    {
        userNews.setCreateTime(DateUtils.getNowDate());
        return userNewsMapper.insertUserNews(userNews);
    }

    /**
     * 修改用户和通知关联
     * 
     * @param userNews 用户和通知关联
     * @return 结果
     */
    @Override
    public int updateUserNews(UserNews userNews)
    {
        return userNewsMapper.updateUserNews(userNews);
    }

    /**
     * 删除用户和通知关联对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteUserNewsByIds(String ids)
    {
        return userNewsMapper.deleteUserNewsByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户和通知关联信息
     * 
     * @param id 用户和通知关联ID
     * @return 结果
     */
    @Override
    public int deleteUserNewsById(Long id)
    {
        return userNewsMapper.deleteUserNewsById(id);
    }
}
