package com.trace.mapper;

import java.util.List;
import com.trace.entity.TraceActivityRecords;

/**
 * 活动交易记录Mapper接口
 * 
 * 
 * @date 2024-10-15
 */
public interface TraceActivityRecordsMapper 
{
    /**
     * 查询活动交易记录
     * 
     * @param id 活动交易记录主键
     * @return 活动交易记录
     */
    public TraceActivityRecords selectTraceActivityRecordsById(Long id);

    /**
     * 查询活动交易记录列表
     * 
     * @param traceActivityRecords 活动交易记录
     * @return 活动交易记录集合
     */
    public List<TraceActivityRecords> selectTraceActivityRecordsList(TraceActivityRecords traceActivityRecords);

    /**
     * 新增活动交易记录
     * 
     * @param traceActivityRecords 活动交易记录
     * @return 结果
     */
    public int insertTraceActivityRecords(TraceActivityRecords traceActivityRecords);

    /**
     * 修改活动交易记录
     * 
     * @param traceActivityRecords 活动交易记录
     * @return 结果
     */
    public int updateTraceActivityRecords(TraceActivityRecords traceActivityRecords);

    /**
     * 删除活动交易记录
     * 
     * @param id 活动交易记录主键
     * @return 结果
     */
    public int deleteTraceActivityRecordsById(Long id);

    /**
     * 批量删除活动交易记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceActivityRecordsByIds(Long[] ids);
}
