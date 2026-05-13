package com.trace.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.trace.common.annotation.Excel;
import com.trace.common.core.domain.BaseEntity;

/**
 * 农产品管理对象 trace_product_list
 * 
 * 
 * @date 2024-10-13
 */
public class TraceProductList extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 农产品编码 */
    @Excel(name = "农产品编码")
    private String code;

    /** 农产品名称 */
    @Excel(name = "农产品名称")
    private String name;

    private Integer checkStatus;

    private Integer machiningStatus;

    private Integer saleStatus;


    public Integer getCheckStatus() {
        return checkStatus;
    }

    public void setCheckStatus(Integer checkStatus) {
        this.checkStatus = checkStatus;
    }

    public Integer getMachiningStatus() {
        return machiningStatus;
    }

    public void setMachiningStatus(Integer machiningStatus) {
        this.machiningStatus = machiningStatus;
    }

    public Integer getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(Integer saleStatus) {
        this.saleStatus = saleStatus;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
