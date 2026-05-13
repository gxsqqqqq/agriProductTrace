package com.trace.mapper;

import com.trace.entity.TraceProductList;
import com.trace.entity.TraceProducts;

import java.util.List;

/**
 * 农产品Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-09
 */
public interface TraceProductListMapper
{
    /**
     * 查询农产品
     * 
     * @param id 农产品主键
     * @return 农产品
     */
    public TraceProductList selectTraceProductListById(Long id);

    /**
     * 查询农产品列表
     * 
     * @param traceProducts 农产品
     * @return 农产品集合
     */
    public List<TraceProductList> selectTraceProductListList(TraceProductList traceProducts);

    /**
     * 新增农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    public int insertTraceProductList(TraceProductList traceProducts);

    /**
     * 修改农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    public int updateTraceProductList(TraceProductList traceProducts);

    /**
     * 删除农产品
     * 
     * @param id 农产品主键
     * @return 结果
     */
    public int deleteTraceProductListById(Long id);

    /**
     * 批量删除农产品
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceProductListByIds(Long[] ids);
}
