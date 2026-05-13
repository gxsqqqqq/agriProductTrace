package com.trace.mapper;

import java.util.List;
import com.trace.entity.TraceActivity;

/**
 * 营销活动Mapper接口
 * 
 * 
 * @date 2024-10-15
 */
public interface TraceActivityMapper 
{
    /**
     * 查询营销活动
     * 
     * @param id 营销活动主键
     * @return 营销活动
     */
    public TraceActivity selectTraceActivityById(Long id);

    /**
     * 查询营销活动列表
     * 
     * @param traceActivity 营销活动
     * @return 营销活动集合
     */
    public List<TraceActivity> selectTraceActivityList(TraceActivity traceActivity);

    /**
     * 新增营销活动
     * 
     * @param traceActivity 营销活动
     * @return 结果
     */
    public int insertTraceActivity(TraceActivity traceActivity);

    /**
     * 修改营销活动
     * 
     * @param traceActivity 营销活动
     * @return 结果
     */
    public int updateTraceActivity(TraceActivity traceActivity);

    /**
     * 删除营销活动
     * 
     * @param id 营销活动主键
     * @return 结果
     */
    public int deleteTraceActivityById(Long id);

    /**
     * 批量删除营销活动
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceActivityByIds(Long[] ids);
}
