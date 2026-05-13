package com.trace.service.impl;

import java.util.List;
import com.trace.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceProductListMapper;
import com.trace.entity.TraceProductList;
import com.trace.service.ITraceProductListService;

/**
 * 农产品管理Service业务层处理
 * 
 * 
 * @date 2024-10-13
 */
@Service
public class TraceProductListServiceImpl implements ITraceProductListService 
{
    @Autowired
    private TraceProductListMapper traceProductListMapper;

    /**
     * 查询农产品管理
     * 
     * @param id 农产品管理主键
     * @return 农产品管理
     */
    @Override
    public TraceProductList selectTraceProductListById(Long id)
    {
        return traceProductListMapper.selectTraceProductListById(id);
    }

    /**
     * 查询农产品管理列表
     * 
     * @param traceProductList 农产品管理
     * @return 农产品管理
     */
    @Override
    public List<TraceProductList> selectTraceProductListList(TraceProductList traceProductList)
    {
        return traceProductListMapper.selectTraceProductListList(traceProductList);
    }

    /**
     * 新增农产品管理
     * 
     * @param traceProductList 农产品管理
     * @return 结果
     */
    @Override
    public int insertTraceProductList(TraceProductList traceProductList)
    {
        traceProductList.setCreateTime(DateUtils.getNowDate());
        return traceProductListMapper.insertTraceProductList(traceProductList);
    }

    /**
     * 修改农产品管理
     * 
     * @param traceProductList 农产品管理
     * @return 结果
     */
    @Override
    public int updateTraceProductList(TraceProductList traceProductList)
    {
        traceProductList.setUpdateTime(DateUtils.getNowDate());
        return traceProductListMapper.updateTraceProductList(traceProductList);
    }

    /**
     * 批量删除农产品管理
     * 
     * @param ids 需要删除的农产品管理主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductListByIds(Long[] ids)
    {
        return traceProductListMapper.deleteTraceProductListByIds(ids);
    }

    /**
     * 删除农产品管理信息
     * 
     * @param id 农产品管理主键
     * @return 结果
     */
    @Override
    public int deleteTraceProductListById(Long id)
    {
        return traceProductListMapper.deleteTraceProductListById(id);
    }
}
