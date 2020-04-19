package edu.yctc.project.system.userNews.service;

import edu.yctc.project.system.userNews.domain.UserNews;

import java.util.List;

/**
 * 用户和通知关联Service接口
 * 
 * @author yctc
 * @date 2020-04-19
 */
public interface IUserNewsService 
{
    /**
     * 查询用户和通知关联
     * 
     * @param id 用户和通知关联ID
     * @return 用户和通知关联
     */
    public UserNews selectUserNewsById(Long id);

    /**
     * 查询用户和通知关联列表
     * 
     * @param userNews 用户和通知关联
     * @return 用户和通知关联集合
     */
    public List<UserNews> selectUserNewsList(UserNews userNews);

    /**
     * 新增用户和通知关联
     * 
     * @param userNews 用户和通知关联
     * @return 结果
     */
    public int insertUserNews(UserNews userNews);

    /**
     * 修改用户和通知关联
     * 
     * @param userNews 用户和通知关联
     * @return 结果
     */
    public int updateUserNews(UserNews userNews);

    /**
     * 批量删除用户和通知关联
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteUserNewsByIds(String ids);

    /**
     * 删除用户和通知关联信息
     * 
     * @param id 用户和通知关联ID
     * @return 结果
     */
    public int deleteUserNewsById(Long id);
}
