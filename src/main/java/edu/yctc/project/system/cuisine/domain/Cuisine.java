package edu.yctc.project.system.cuisine.domain;

import edu.yctc.framework.aspectj.lang.annotation.Excel;
import edu.yctc.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 菜品信息对象 sys_cuisine
 * 
 * @author luna
 * @date 2020-03-28
 */
public class Cuisine extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 菜品id */
    private Long cuisineId;

    /** 菜品名称 */
    @Excel(name = "菜品名称")
    private String cuisineName;

    /** 菜品图片路径 */
    @Excel(name = "菜品图片路径")
    private String cuisinePath;

    /** 今日菜品份数 */
    @Excel(name = "今日菜品份数")
    private Integer cuisineNumber;

    /** 菜品价格 */
    @Excel(name = "菜品价格")
    private Double cuisinePrice;

    public void setCuisineId(Long cuisineId) 
    {
        this.cuisineId = cuisineId;
    }

    public Long getCuisineId() 
    {
        return cuisineId;
    }
    public void setCuisineName(String cuisineName) 
    {
        this.cuisineName = cuisineName;
    }

    public String getCuisineName() 
    {
        return cuisineName;
    }
    public void setCuisinePath(String cuisinePath) 
    {
        this.cuisinePath = cuisinePath;
    }

    public String getCuisinePath() 
    {
        return cuisinePath;
    }
    public void setCuisineNumber(Integer cuisineNumber) 
    {
        this.cuisineNumber = cuisineNumber;
    }

    public Integer getCuisineNumber() 
    {
        return cuisineNumber;
    }
    public void setCuisinePrice(Double cuisinePrice) 
    {
        this.cuisinePrice = cuisinePrice;
    }

    public Double getCuisinePrice() 
    {
        return cuisinePrice;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
            .append("cuisineId", getCuisineId())
            .append("cuisineName", getCuisineName())
            .append("cuisinePath", getCuisinePath())
            .append("cuisineNumber", getCuisineNumber())
            .append("cuisinePrice", getCuisinePrice())
            .toString();
    }
}
