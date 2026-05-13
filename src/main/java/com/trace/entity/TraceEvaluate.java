package com.trace.entity;

import com.trace.common.annotation.Excel;
import com.trace.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 消费者评价对象 trace_evaluate
 * 
 * 
 */
public class TraceEvaluate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 昵称 */
    @Excel(name = "昵称")
    private String nickName;

    /** 评价内容 */
    @Excel(name = "评价内容")
    private String content;

    /** 区块链hash */
    @Excel(name = "区块链hash")
    private String blockHash;

    /** 区块高度 */
    @Excel(name = "区块高度")
    private Long blockNumber;

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
    public void setNickName(String nickName) 
    {
        this.nickName = nickName;
    }

    public String getNickName() 
    {
        return nickName;
    }
    public void setContent(String content) 
    {
        this.content = content;
    }

    public String getContent() 
    {
        return content;
    }
    public void setBlockHash(String blockHash) 
    {
        this.blockHash = blockHash;
    }

    public String getBlockHash() 
    {
        return blockHash;
    }
    public void setBlockNumber(Long blockNumber) 
    {
        this.blockNumber = blockNumber;
    }

    public Long getBlockNumber() 
    {
        return blockNumber;
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
            .append("nickName", getNickName())
            .append("content", getContent())
            .append("blockHash", getBlockHash())
            .append("blockNumber", getBlockNumber())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .toString();
    }
}
