package com.trace.mapper;

import com.trace.entity.TraceAlaramrecords;

import java.util.List;

/**
 * 告警记录Mapper接口
 * 
 * 
 * @date 2024-10-22
 */
public interface TraceAlaramrecordsMapper 
{
    /**
     * 查询告警记录
     * 
     * @param id 告警记录主键
     * @return 告警记录
     */
    public TraceAlaramrecords selectTraceAlaramrecordsById(Long id);

    /**
     * 查询告警记录列表
     * 
     * @param traceAlaramrecords 告警记录
     * @return 告警记录集合
     */
    public List<TraceAlaramrecords> selectTraceAlaramrecordsList(TraceAlaramrecords traceAlaramrecords);

    /**
     * 新增告警记录
     * 
     * @param traceAlaramrecords 告警记录
     * @return 结果
     */
    public int insertTraceAlaramrecords(TraceAlaramrecords traceAlaramrecords);

    /**
     * 修改告警记录
     * 
     * @param traceAlaramrecords 告警记录
     * @return 结果
     */
    public int updateTraceAlaramrecords(TraceAlaramrecords traceAlaramrecords);

    /**
     * 删除告警记录
     * 
     * @param id 告警记录主键
     * @return 结果
     */
    public int deleteTraceAlaramrecordsById(Long id);

    /**
     * 批量删除告警记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceAlaramrecordsByIds(Long[] ids);
}
