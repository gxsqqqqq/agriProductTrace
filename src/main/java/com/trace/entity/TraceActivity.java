package com.trace.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.trace.common.annotation.Excel;
import com.trace.common.core.domain.BaseEntity;

/**
 * 营销活动对象 trace_activity
 * 
 * 
 * @date 2024-10-15
 */
public class TraceActivity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 活动id */
    private Long id;

    /** 活动名称 */
    @Excel(name = "活动名称")
    private String activityName;

    /** 产品名称 */
    @Excel(name = "产品名称")
    private String productName;

    /** 商品图片 */
    @Excel(name = "商品图片")
    private String productUrl;

    /** 商品数量 */
    @Excel(name = "商品数量")
    private Long num;

    /** 所需积分 */
    @Excel(name = "所需积分")
    private String integral;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setActivityName(String activityName) 
    {
        this.activityName = activityName;
    }

    public String getActivityName() 
    {
        return activityName;
    }
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setProductUrl(String productUrl) 
    {
        this.productUrl = productUrl;
    }

    public String getProductUrl() 
    {
        return productUrl;
    }
    public void setNum(Long num) 
    {
        this.num = num;
    }

    public Long getNum() 
    {
        return num;
    }
    public void setIntegral(String integral) 
    {
        this.integral = integral;
    }

    public String getIntegral() 
    {
        return integral;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("activityName", getActivityName())
            .append("productName", getProductName())
            .append("productUrl", getProductUrl())
            .append("num", getNum())
            .append("integral", getIntegral())
            .toString();
    }
}
