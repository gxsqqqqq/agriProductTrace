package com.trace.service.impl;

import com.trace.entity.TraceWelfare;
import com.trace.mapper.TraceWelfareMapper;
import com.trace.service.ITraceWelfareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 福利中心Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class TraceWelfareServiceImpl implements ITraceWelfareService
{
    @Autowired
    private TraceWelfareMapper traceWelfareMapper;

    /**
     * 查询福利中心
     * 
     * @param id 福利中心主键
     * @return 福利中心
     */
    @Override
    public TraceWelfare selectTraceWelfareById(Long id)
    {
        return traceWelfareMapper.selectTraceWelfareById(id);
    }

    /**
     * 查询福利中心列表
     * 
     * @param traceWelfare 福利中心
     * @return 福利中心
     */
    @Override
    public List<TraceWelfare> selectTraceWelfareList(TraceWelfare traceWelfare)
    {
        return traceWelfareMapper.selectTraceWelfareList(traceWelfare);
    }

    /**
     * 新增福利中心
     * 
     * @param traceWelfare 福利中心
     * @return 结果
     */
    @Override
    public int insertTraceWelfare(TraceWelfare traceWelfare)
    {
        return traceWelfareMapper.insertTraceWelfare(traceWelfare);
    }

    /**
     * 修改福利中心
     * 
     * @param traceWelfare 福利中心
     * @return 结果
     */
    @Override
    public int updateTraceWelfare(TraceWelfare traceWelfare)
    {
        return traceWelfareMapper.updateTraceWelfare(traceWelfare);
    }

    /**
     * 批量删除福利中心
     * 
     * @param ids 需要删除的福利中心主键
     * @return 结果
     */
    @Override
    public int deleteTraceWelfareByIds(Long[] ids)
    {
        return traceWelfareMapper.deleteTraceWelfareByIds(ids);
    }

    /**
     * 删除福利中心信息
     * 
     * @param id 福利中心主键
     * @return 结果
     */
    @Override
    public int deleteTraceWelfareById(Long id)
    {
        return traceWelfareMapper.deleteTraceWelfareById(id);
    }
}
