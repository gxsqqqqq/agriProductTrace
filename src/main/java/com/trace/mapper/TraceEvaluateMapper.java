package com.trace.mapper;

import com.trace.entity.TraceEvaluate;

import java.util.List;

/**
 * 消费者评价Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
public interface TraceEvaluateMapper 
{
    /**
     * 查询消费者评价
     * 
     * @param id 消费者评价主键
     * @return 消费者评价
     */
    public TraceEvaluate selectTraceEvaluateById(Long id);

    /**
     * 查询消费者评价列表
     * 
     * @param traceEvaluate 消费者评价
     * @return 消费者评价集合
     */
    public List<TraceEvaluate> selectTraceEvaluateList(TraceEvaluate traceEvaluate);

    /**
     * 新增消费者评价
     * 
     * @param traceEvaluate 消费者评价
     * @return 结果
     */
    public int insertTraceEvaluate(TraceEvaluate traceEvaluate);

    /**
     * 修改消费者评价
     * 
     * @param traceEvaluate 消费者评价
     * @return 结果
     */
    public int updateTraceEvaluate(TraceEvaluate traceEvaluate);

    /**
     * 删除消费者评价
     * 
     * @param id 消费者评价主键
     * @return 结果
     */
    public int deleteTraceEvaluateById(Long id);

    /**
     * 批量删除消费者评价
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceEvaluateByIds(Long[] ids);
}
