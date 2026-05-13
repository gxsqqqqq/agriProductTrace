package com.trace.entity;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 积分转让统计对象 trace_trans
 * 
 * @author ruoyi
 * @date 2024-10-24
 */
public class TraceTrans extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 转让人 */
    @Excel(name = "转让人")
    private String fromUser;

    /** 接收人 */
    @Excel(name = "接收人")
    private String toUser;

    /** 转让积分数量 */
    @Excel(name = "转让积分数量")
    private Long amount;

    /** 所在区块 */
    @Excel(name = "所在区块")
    private String blockNumber;

    /** 交易hash */
    @Excel(name = "交易hash")
    private String blockHash;

    /** 扩展1 */
    @Excel(name = "扩展1")
    private String ext1;

    /** 扩展2 */
    @Excel(name = "扩展2")
    private String ext2;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setFromUser(String fromUser)
    {
        this.fromUser = fromUser;
    }

    public String getFromUser()
    {
        return fromUser;
    }
    public void setToUser(String toUser) 
    {
        this.toUser = toUser;
    }

    public String getToUser() 
    {
        return toUser;
    }
    public void setAmount(Long amount) 
    {
        this.amount = amount;
    }

    public Long getAmount() 
    {
        return amount;
    }
    public void setBlockNumber(String blockNumber) 
    {
        this.blockNumber = blockNumber;
    }

    public String getBlockNumber() 
    {
        return blockNumber;
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
            .append("fromUser", getFromUser())
            .append("toUser", getToUser())
            .append("amount", getAmount())
            .append("blockNumber", getBlockNumber())
            .append("blockHash", getBlockHash())
            .append("remark", getRemark())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .append("createTime", getCreateTime())
            .toString();
    }
}
