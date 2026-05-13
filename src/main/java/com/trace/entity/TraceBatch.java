package com.trace.entity;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 批次信息对象 trace_batch
 * 
 * @author ruoyi
 * @date 2024-10-13
 */
public class TraceBatch extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 农产品id */
    @Excel(name = "农产品id")
    private Long productId;

    private String batchNo;

    private String batchPrefix = "TRACE";

    private Integer batchNum = 5;

    /** 农产品名字 */
    @Excel(name = "农产品名字")
    private String productName;

    /** 区块链hash */
    @Excel(name = "区块链hash")
    private String blockHash;

    /** 区块链数据 */
    @Excel(name = "区块链数据")
    private String blockData;

    /** 数据 */
    @Excel(name = "数据")
    private String dataJson;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ext1;

    /** $column.columnComment */
    @Excel(name = "${comment}", readConverterExp = "$column.readConverterExp()")
    private String ext2;

    public Integer getBatchNum() {
        return batchNum;
    }

    public void setBatchNum(Integer batchNum) {
        this.batchNum = batchNum;
    }

    public String getBatchPrefix() {
        return batchPrefix;
    }

    public void setBatchPrefix(String batchPrefix) {
        this.batchPrefix = batchPrefix;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

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
    public void setProductName(String productName) 
    {
        this.productName = productName;
    }

    public String getProductName() 
    {
        return productName;
    }
    public void setBlockHash(String blockHash) 
    {
        this.blockHash = blockHash;
    }

    public String getBlockHash() 
    {
        return blockHash;
    }
    public void setBlockData(String blockData) 
    {
        this.blockData = blockData;
    }

    public String getBlockData() 
    {
        return blockData;
    }
    public void setDataJson(String dataJson) 
    {
        this.dataJson = dataJson;
    }

    public String getDataJson() 
    {
        return dataJson;
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
            .append("productId", getProductId())
            .append("productName", getProductName())
            .append("blockHash", getBlockHash())
            .append("blockData", getBlockData())
            .append("dataJson", getDataJson())
            .append("ext1", getExt1())
            .append("ext2", getExt2())
            .toString();
    }
}
