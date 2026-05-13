package com.trace.service.impl;

import com.trace.entity.TraceAlarm;
import com.trace.mapper.TraceAlarmMapper;
import com.trace.service.ITraceAlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 告警规则Service业务层处理
 * 
 * 
 * @date 2024-10-22
 */
@Service
public class TraceAlarmServiceImpl implements ITraceAlarmService 
{
    @Autowired
    private TraceAlarmMapper traceAlarmMapper;

    /**
     * 查询告警规则
     * 
     * @param id 告警规则主键
     * @return 告警规则
     */
    @Override
    public TraceAlarm selectTraceAlarmById(Long id)
    {
        return traceAlarmMapper.selectTraceAlarmById(id);
    }

    /**
     * 查询告警规则列表
     * 
     * @param traceAlarm 告警规则
     * @return 告警规则
     */
    @Override
    public List<TraceAlarm> selectTraceAlarmList(TraceAlarm traceAlarm)
    {
        return traceAlarmMapper.selectTraceAlarmList(traceAlarm);
    }

    /**
     * 新增告警规则
     * 
     * @param traceAlarm 告警规则
     * @return 结果
     */
    @Override
    public int insertTraceAlarm(TraceAlarm traceAlarm)
    {
        return traceAlarmMapper.insertTraceAlarm(traceAlarm);
    }

    /**
     * 修改告警规则
     * 
     * @param traceAlarm 告警规则
     * @return 结果
     */
    @Override
    public int updateTraceAlarm(TraceAlarm traceAlarm)
    {
        return traceAlarmMapper.updateTraceAlarm(traceAlarm);
    }

    /**
     * 批量删除告警规则
     * 
     * @param ids 需要删除的告警规则主键
     * @return 结果
     */
    @Override
    public int deleteTraceAlarmByIds(Long[] ids)
    {
        return traceAlarmMapper.deleteTraceAlarmByIds(ids);
    }

    /**
     * 删除告警规则信息
     * 
     * @param id 告警规则主键
     * @return 结果
     */
    @Override
    public int deleteTraceAlarmById(Long id)
    {
        return traceAlarmMapper.deleteTraceAlarmById(id);
    }
}
