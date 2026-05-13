package com.trace.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceProductsMapper;
import com.trace.entity.TraceProducts;
import com.trace.service.ITraceProductsService;

/**
 * 农产品Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-09
 */
@Service
public class TraceProductsServiceImpl implements ITraceProductsService 
{
    @Autowired
    private TraceProductsMapper traceProductsMapper;

    /**
     * 查询农产品
     * 
     * @param id 农产品主键
     * @return 农产品
     */
    @Override
    public TraceProducts selectTraceProductsById(Long id)
    {
        return traceProductsMapper.selectTraceProductsById(id);
    }

    /**
     * 查询农产品列表
     * 
     * @param traceProducts 农产品
     * @return 农产品
     */
    @Override
    public List<TraceProducts> selectTraceProductsList(TraceProducts traceProducts)
    {
        return traceProductsMapper.selectTraceProductsList(traceProducts);
    }

    /**
     * 新增农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    @Override
    public int insertTraceProducts(TraceProducts traceProducts)
    {
        traceProducts.setCreateTime(DateUtils.getNowDate());
        return traceProductsMapper.insertTraceProducts(traceProducts);
    }

    /**
     * 修改农产品
     * 
     * @param traceProducts 农产品
     * @return 结果
     */
    @Override
    public int updateTraceProducts(TraceProducts traceProducts)
    {
        traceProducts.setUpdateTime(DateUtils.getNowDate());
        return traceProductsMapper.updateTraceProducts(traceProducts);
    }

    /**
     * 批量删除农产品
     * 
     * @param ids 需要删除的农产品主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductsByIds(Long[] ids)
    {
        return traceProductsMapper.deleteTraceProductsByIds(ids);
    }

    /**
     * 删除农产品信息
     * 
     * @param id 农产品主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductsById(Long id)
    {
        return traceProductsMapper.deleteTraceProductsById(id);
    }
}
