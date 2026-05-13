package com.trace.service.impl;

import com.trace.entity.TraceEvaluate;
import com.trace.mapper.TraceEvaluateMapper;
import com.trace.service.ITraceEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 消费者评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
@Service
public class TraceEvaluateServiceImpl implements ITraceEvaluateService 
{
    @Autowired
    private TraceEvaluateMapper traceEvaluateMapper;

    /**
     * 查询消费者评价
     * 
     * @param id 消费者评价主键
     * @return 消费者评价
     */
    @Override
    public TraceEvaluate selectTraceEvaluateById(Long id)
    {
        return traceEvaluateMapper.selectTraceEvaluateById(id);
    }

    /**
     * 查询消费者评价列表
     * 
     * @param traceEvaluate 消费者评价
     * @return 消费者评价
     */
    @Override
    public List<TraceEvaluate> selectTraceEvaluateList(TraceEvaluate traceEvaluate)
    {
        return traceEvaluateMapper.selectTraceEvaluateList(traceEvaluate);
    }

    /**
     * 新增消费者评价
     * 
     * @param traceEvaluate 消费者评价
     * @return 结果
     */
    @Override
    public int insertTraceEvaluate(TraceEvaluate traceEvaluate)
    {
        return traceEvaluateMapper.insertTraceEvaluate(traceEvaluate);
    }

    /**
     * 修改消费者评价
     * 
     * @param traceEvaluate 消费者评价
     * @return 结果
     */
    @Override
    public int updateTraceEvaluate(TraceEvaluate traceEvaluate)
    {
        return traceEvaluateMapper.updateTraceEvaluate(traceEvaluate);
    }

    /**
     * 批量删除消费者评价
     * 
     * @param ids 需要删除的消费者评价主键
     * @return 结果
     */
    @Override
    public int deleteTraceEvaluateByIds(Long[] ids)
    {
        return traceEvaluateMapper.deleteTraceEvaluateByIds(ids);
    }

    /**
     * 删除消费者评价信息
     * 
     * @param id 消费者评价主键
     * @return 结果
     */
    @Override
    public int deleteTraceEvaluateById(Long id)
    {
        return traceEvaluateMapper.deleteTraceEvaluateById(id);
    }
}
