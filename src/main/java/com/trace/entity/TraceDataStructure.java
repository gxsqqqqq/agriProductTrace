package com.trace.entity;

import com.alibaba.fastjson2.JSONArray;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 数据结构对象 trace_data_structure
 *
 * @author ruoyi
 * @date 2024-10-07
 */
public class TraceDataStructure extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 岗位ID */
    private Long id;

    /** 编码 */
    @Excel(name = "编码")
    private String code;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    private String node;

    /** 数据结构json */
    @Excel(name = "数据结构json")
    private String dataJson;

    private JSONArray dataJsonArray;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public JSONArray getDataJsonArray() {
        return dataJsonArray;
    }

    public void setDataJsonArray(JSONArray dataJsonArray) {
        this.dataJsonArray = dataJsonArray;
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
    public void setDataJson(String dataJson)
    {
        this.dataJson = dataJson;
    }

    public String getDataJson()
    {
        return dataJson;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("code", getCode())
            .append("name", getName())
            .append("dataJson", getDataJson())
            .append("remark", getRemark())
            .toString();
    }
}
