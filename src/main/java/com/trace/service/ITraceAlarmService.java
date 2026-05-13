package com.trace.service;

import com.trace.entity.TraceAlarm;

import java.util.List;

/**
 * 告警规则Service接口
 * 
 * 
 * @date 2024-10-22
 */
public interface ITraceAlarmService 
{
    /**
     * 查询告警规则
     * 
     * @param id 告警规则主键
     * @return 告警规则
     */
    public TraceAlarm selectTraceAlarmById(Long id);

    /**
     * 查询告警规则列表
     * 
     * @param traceAlarm 告警规则
     * @return 告警规则集合
     */
    public List<TraceAlarm> selectTraceAlarmList(TraceAlarm traceAlarm);

    /**
     * 新增告警规则
     * 
     * @param traceAlarm 告警规则
     * @return 结果
     */
    public int insertTraceAlarm(TraceAlarm traceAlarm);

    /**
     * 修改告警规则
     * 
     * @param traceAlarm 告警规则
     * @return 结果
     */
    public int updateTraceAlarm(TraceAlarm traceAlarm);

    /**
     * 批量删除告警规则
     * 
     * @param ids 需要删除的告警规则主键集合
     * @return 结果
     */
    public int deleteTraceAlarmByIds(Long[] ids);

    /**
     * 删除告警规则信息
     * 
     * @param id 告警规则主键
     * @return 结果
     */
    public int deleteTraceAlarmById(Long id);
}
