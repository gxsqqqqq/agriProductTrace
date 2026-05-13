package com.trace.service;

import com.trace.entity.TraceTrans;

import java.util.List;

/**
 * 积分转让统计Service接口
 * 
 * 
 * @date 2024-10-24
 */
public interface ITraceTransService 
{
    /**
     * 查询积分转让统计
     * 
     * @param id 积分转让统计主键
     * @return 积分转让统计
     */
    public TraceTrans selectTraceTransById(Long id);

    /**
     * 查询积分转让统计列表
     * 
     * @param traceTrans 积分转让统计
     * @return 积分转让统计集合
     */
    public List<TraceTrans> selectTraceTransList(TraceTrans traceTrans);

    /**
     * 新增积分转让统计
     * 
     * @param traceTrans 积分转让统计
     * @return 结果
     */
    public int insertTraceTrans(TraceTrans traceTrans);

    /**
     * 修改积分转让统计
     * 
     * @param traceTrans 积分转让统计
     * @return 结果
     */
    public int updateTraceTrans(TraceTrans traceTrans);

    /**
     * 批量删除积分转让统计
     * 
     * @param ids 需要删除的积分转让统计主键集合
     * @return 结果
     */
    public int deleteTraceTransByIds(Long[] ids);

    /**
     * 删除积分转让统计信息
     * 
     * @param id 积分转让统计主键
     * @return 结果
     */
    public int deleteTraceTransById(Long id);
}
