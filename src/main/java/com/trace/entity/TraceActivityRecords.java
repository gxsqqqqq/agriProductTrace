package com.trace.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.trace.common.annotation.Excel;
import com.trace.common.core.domain.BaseEntity;

/**
 * 活动交易记录对象 trace_activity_records
 * 
 * 
 * @date 2024-10-15
 */
public class TraceActivityRecords extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 电话 */
    @Excel(name = "电话")
    private String tel;

    /** 活动id */
    @Excel(name = "活动id")
    private Long activityId;

    private String activityName;

    private String productName;

    /** 区块链hash */
    @Excel(name = "区块链hash")
    private String blockHash;

    /** 扩展1 */
    @Excel(name = "扩展1")
    private String ext1;

    /** 扩展2 */
    @Excel(name = "扩展2")
    private String ext2;

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTel(String tel) 
    {
        this.tel = tel;
    }

    public String getTel() 
    {
        return tel;
    }
    public void setActivityId(Long activityId) 
    {
        this.activityId = activityId;
    }

    public Long getActivityId() 
    {
        return activityId;
    }
    public void setBlockHash(String blockHash) 
    {
        this.blockHash = blockHash;
    }

    public String getBlockHash() 
    {
        return blockHash;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("tel", getTel())
            .append("activityId", getActivityId())
            .append("blockHash", getBlockHash())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .toString();
    }
}
