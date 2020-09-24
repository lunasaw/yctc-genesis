package edu.yctc.project.system.knowledge.service.impl;

import java.util.List;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.yctc.common.utils.DateUtils;
import edu.yctc.common.utils.text.Convert;
import edu.yctc.project.system.knowledge.domain.Knowledge;
import edu.yctc.project.system.knowledge.mapper.KnowledgeMapper;
import edu.yctc.project.system.knowledge.service.IKnowledgeService;

/**
 * 知识点概览Service业务层处理
 * 
 * @author yctc
 * @date 2020-04-21
 */
@Service
public class KnowledgeServiceImpl implements IKnowledgeService 
{
	private final static Logger log = LoggerFactory.getLogger(KnowledgeServiceImpl.class);

	@Autowired
    private KnowledgeMapper knowledgeMapper;

    /**
     * 查询知识点概览
     * 
     * @param id 知识点概览ID
     * @return 知识点概览
     */
    @Override
    public Knowledge selectKnowledgeById(Long id)
    {
        return knowledgeMapper.selectKnowledgeById(id);
    }

    /**
     * 查询知识点概览列表
     * 
     * @param knowledge 知识点概览
     * @return 知识点概览
     */
    @Override
    public List<Knowledge> selectKnowledgeList(Knowledge knowledge)
    {
        return knowledgeMapper.selectKnowledgeList(knowledge);
    }

    /**
     * 新增知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    @Override
    public int insertKnowledge(Knowledge knowledge)
    {
        knowledge.setCreateTime(DateUtils.getNowDate());
        return knowledgeMapper.insertKnowledge(knowledge);
    }

    /**
     * 修改知识点概览
     * 
     * @param knowledge 知识点概览
     * @return 结果
     */
    @Override
    public int updateKnowledge(Knowledge knowledge)
    {
        return knowledgeMapper.updateKnowledge(knowledge);
    }

    /**
     * 删除知识点概览对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeByIds(String ids)
    {
        return knowledgeMapper.deleteKnowledgeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除知识点概览信息
     * 
     * @param id 知识点概览ID
     * @return 结果
     */
    @Override
    public int deleteKnowledgeById(Long id)
    {
        return knowledgeMapper.deleteKnowledgeById(id);
    }



	/** 匹配中文正则表达式 */
	private final static String PATTERN = "[\\u4e00-\\u9fa5]+";

	@Override
	public boolean checkKnowledge(String knowledge, String toMatch) {
		if (StringUtils.isBlank(knowledge) || StringUtils.isBlank(toMatch)) {
			log.error("check knowledge fail, parameter invalid, knowledge={}, toMatch={}", knowledge, toMatch);
			return false;
		}
		Pattern pattern = Pattern.compile(PATTERN);
		// OCR识别出的文字用换行符分隔
		String[] split = toMatch.split("\n");
		for (String str : split) {
			if (pattern.matcher(str).find()) {
				// 匹配到中文
				// 判断是否是知识点
				if (str.replaceAll(" ", "").contains(knowledge.replaceAll(" ", ""))) {
					log.info("check knowledge success, knowledge={}, toMatch={}", knowledge, toMatch);
					return true;
				}
			}
		}
		log.error("check knowledge fail, no such knowledge, knowledge={}, toMatch={}", knowledge, toMatch);
		return false;
	}
}
