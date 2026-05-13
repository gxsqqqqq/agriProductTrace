package com.trace.service;

import java.util.List;
import com.trace.entity.TraceProducts;

/**
 * 农产品Service接口
 * 
 * 
 * @date 2024-10-09
 */
public interface ITraceProductsService 
{
    /**
     * 查询农产品
     * 
     * @param id 农产品主键
     * @return 农产品
     */
    public TraceProducts selectTraceProductsById(Long id);

    /**
     * 查询农产品列表
     * 
     * @param traceProducts 农产品
     * @return 农产品集合
     */
    public List<TraceProducts> selectTraceProductsList(TraceProducts traceProducts);

    /**
     * 新增农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    public int insertTraceProducts(TraceProducts traceProducts);

    /**
     * 修改农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    public int updateTraceProducts(TraceProducts traceProducts);

    /**
     * 批量删除农产品
     * 
     * @param ids 需要删除的农产品主键集合
     * @return 结果
     */
    public int deleteTraceProductsByIds(Long[] ids);

    /**
     * 删除农产品信息
     * 
     * @param id 农产品主键
     * @return 结果
     */
    public int deleteTraceProductsById(Long id);
}
