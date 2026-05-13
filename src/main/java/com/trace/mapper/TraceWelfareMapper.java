package com.trace.mapper;

import com.trace.entity.TraceWelfare;

import java.util.List;

/**
 * 福利中心Mapper接口
 * 
 * @author ruoyi
 * @date 2024-10-16
 */
public interface TraceWelfareMapper 
{
    /**
     * 查询福利中心
     * 
     * @param id 福利中心主键
     * @return 福利中心
     */
    public TraceWelfare selectTraceWelfareById(Long id);

    /**
     * 查询福利中心列表
     * 
     * @param traceWelfare 福利中心
     * @return 福利中心集合
     */
    public List<TraceWelfare> selectTraceWelfareList(TraceWelfare traceWelfare);

    /**
     * 新增福利中心
     * 
     * @param traceWelfare 福利中心
     * @return 结果
     */
    public int insertTraceWelfare(TraceWelfare traceWelfare);

    /**
     * 修改福利中心
     * 
     * @param traceWelfare 福利中心
     * @return 结果
     */
    public int updateTraceWelfare(TraceWelfare traceWelfare);

    /**
     * 删除福利中心
     * 
     * @param id 福利中心主键
     * @return 结果
     */
    public int deleteTraceWelfareById(Long id);

    /**
     * 批量删除福利中心
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceWelfareByIds(Long[] ids);
}
