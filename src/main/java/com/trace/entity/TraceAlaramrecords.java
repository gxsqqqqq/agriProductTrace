package com.trace.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 告警记录对象 trace_alaramrecords
 * 
 * @author ruoyi
 * @date 2024-10-22
 */
public class TraceAlaramrecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 产品 */
    @Excel(name = "产品")
    private Long productId;

    /** 规则id */
    @Excel(name = "规则id")
    private Long ruleId;

    /** 规则详情 */
    @Excel(name = "规则详情")
    private String ruleDetail;

    /**  */
    @Excel(name = "")
    private String ext1;

    /**  */
    @Excel(name = "")
    private String ext2;

    /**  */
    @Excel(name = "")
    private String ext3;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }
    public void setRuleId(Long ruleId) 
    {
        this.ruleId = ruleId;
    }

    public Long getRuleId() 
    {
        return ruleId;
    }
    public void setRuleDetail(String ruleDetail) 
    {
        this.ruleDetail = ruleDetail;
    }

    public String getRuleDetail() 
    {
        return ruleDetail;
    }
    public void setExt1(String ext1) 
    {
        this.ext1 = ext1;
    }

    public String getExt1() 
    {
        return ext1;
    }
    public void setExt2(String ext2) 
    {
        this.ext2 = ext2;
    }

    public String getExt2() 
    {
        return ext2;
    }
    public void setExt3(String ext3) 
    {
        this.ext3 = ext3;
    }

    public String getExt3() 
    {
        return ext3;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productId", getProductId())
            .append("ruleId", getRuleId())
            .append("ruleDetail", getRuleDetail())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .append("ext3", getExt3())
            .toString();
    }
}
