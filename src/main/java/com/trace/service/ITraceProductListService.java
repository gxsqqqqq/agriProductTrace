package com.trace.service;

import java.util.List;
import com.trace.entity.TraceProductList;

/**
 * 农产品管理Service接口
 * 
 * 
 * @date 2024-10-13
 */
public interface ITraceProductListService 
{
    /**
     * 查询农产品管理
     * 
     * @param id 农产品管理主键
     * @return 农产品管理
     */
    public TraceProductList selectTraceProductListById(Long id);

    /**
     * 查询农产品管理列表
     * 
     * @param traceProductList 农产品管理
     * @return 农产品管理集合
     */
    public List<TraceProductList> selectTraceProductListList(TraceProductList traceProductList);

    /**
     * 新增农产品管理
     * 
     * @param traceProductList 农产品管理
     * @return 结果
     */
    public int insertTraceProductList(TraceProductList traceProductList);

    /**
     * 修改农产品管理
     * 
     * @param traceProductList 农产品管理
     * @return 结果
     */
    public int updateTraceProductList(TraceProductList traceProductList);

    /**
     * 批量删除农产品管理
     * 
     * @param ids 需要删除的农产品管理主键集合
     * @return 结果
     */
    public int deleteTraceProductListByIds(Long[] ids);

    /**
     * 删除农产品管理信息
     * 
     * @param id 农产品管理主键
     * @return 结果
     */
    public int deleteTraceProductListById(Long id);
}
