package com.trace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceActivityMapper;
import com.trace.entity.TraceActivity;
import com.trace.service.ITraceActivityService;

/**
 * 营销活动Service业务层处理
 * 
 * @author ruoyi
 * @date 2024-10-15
 */
@Service
public class TraceActivityServiceImpl implements ITraceActivityService 
{
    @Autowired
    private TraceActivityMapper traceActivityMapper;

    /**
     * 查询营销活动
     * 
     * @param id 营销活动主键
     * @return 营销活动
     */
    @Override
    public TraceActivity selectTraceActivityById(Long id)
    {
        return traceActivityMapper.selectTraceActivityById(id);
    }

    /**
     * 查询营销活动列表
     * 
     * @param traceActivity 营销活动
     * @return 营销活动
     */
    @Override
    public List<TraceActivity> selectTraceActivityList(TraceActivity traceActivity)
    {
        return traceActivityMapper.selectTraceActivityList(traceActivity);
    }

    /**
     * 新增营销活动
     * 
     * @param traceActivity 营销活动
     * @return 结果
     */
    @Override
    public int insertTraceActivity(TraceActivity traceActivity)
    {
        return traceActivityMapper.insertTraceActivity(traceActivity);
    }

    /**
     * 修改营销活动
     * 
     * @param traceActivity 营销活动
     * @return 结果
     */
    @Override
    public int updateTraceActivity(TraceActivity traceActivity)
    {
        return traceActivityMapper.updateTraceActivity(traceActivity);
    }

    /**
     * 批量删除营销活动
     * 
     * @param ids 需要删除的营销活动主键
     * @return 结果
     */
    @Override
    public int deleteTraceActivityByIds(Long[] ids)
    {
        return traceActivityMapper.deleteTraceActivityByIds(ids);
    }

    /**
     * 删除营销活动信息
     * 
     * @param id 营销活动主键
     * @return 结果
     */
    @Override
    public int deleteTraceActivityById(Long id)
    {
        return traceActivityMapper.deleteTraceActivityById(id);
    }
}
