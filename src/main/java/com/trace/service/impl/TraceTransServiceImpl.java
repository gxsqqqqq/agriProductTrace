package com.trace.service.impl;

import com.trace.common.utils.DateUtils;
import com.trace.entity.TraceTrans;
import com.trace.mapper.TraceTransMapper;
import com.trace.service.ITraceTransService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 积分转让统计Service业务层处理
 * 
 * 
 * @date 2024-10-24
 */
@Service
public class TraceTransServiceImpl implements ITraceTransService 
{
    @Autowired
    private TraceTransMapper traceTransMapper;

    /**
     * 查询积分转让统计
     * 
     * @param id 积分转让统计主键
     * @return 积分转让统计
     */
    @Override
    public TraceTrans selectTraceTransById(Long id)
    {
        return traceTransMapper.selectTraceTransById(id);
    }

    /**
     * 查询积分转让统计列表
     * 
     * @param traceTrans 积分转让统计
     * @return 积分转让统计
     */
    @Override
    public List<TraceTrans> selectTraceTransList(TraceTrans traceTrans)
    {
        return traceTransMapper.selectTraceTransList(traceTrans);
    }

    /**
     * 新增积分转让统计
     * 
     * @param traceTrans 积分转让统计
     * @return 结果
     */
    @Override
    public int insertTraceTrans(TraceTrans traceTrans)
    {
        traceTrans.setCreateTime(DateUtils.getNowDate());
        return traceTransMapper.insertTraceTrans(traceTrans);
    }

    /**
     * 修改积分转让统计
     * 
     * @param traceTrans 积分转让统计
     * @return 结果
     */
    @Override
    public int updateTraceTrans(TraceTrans traceTrans)
    {
        return traceTransMapper.updateTraceTrans(traceTrans);
    }

    /**
     * 批量删除积分转让统计
     * 
     * @param ids 需要删除的积分转让统计主键
     * @return 结果
     */
    @Override
    public int deleteTraceTransByIds(Long[] ids)
    {
        return traceTransMapper.deleteTraceTransByIds(ids);
    }

    /**
     * 删除积分转让统计信息
     * 
     * @param id 积分转让统计主键
     * @return 结果
     */
    @Override
    public int deleteTraceTransById(Long id)
    {
        return traceTransMapper.deleteTraceTransById(id);
    }
}
