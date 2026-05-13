package com.trace.service.impl;

import java.util.Collections;
import java.util.List;

import com.trace.dto.RankDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceBatchMapper;
import com.trace.entity.TraceBatch;
import com.trace.service.ITraceBatchService;

/**
 * 批次信息Service业务层处理
 * 
 * 
 * @date 2024-10-13
 */
@Service
public class TraceBatchServiceImpl implements ITraceBatchService 
{
    @Autowired
    private TraceBatchMapper traceBatchMapper;

    /**
     * 查询批次信息
     * 
     * @param id 批次信息主键
     * @return 批次信息
     */
    @Override
    public TraceBatch selectTraceBatchById(Long id)
    {
        return traceBatchMapper.selectTraceBatchById(id);
    }

    /**
     * 查询批次信息列表
     * 
     * @param traceBatch 批次信息
     * @return 批次信息
     */
    @Override
    public List<TraceBatch> selectTraceBatchList(TraceBatch traceBatch)
    {
        return traceBatchMapper.selectTraceBatchList(traceBatch);
    }

    /**
     * 新增批次信息
     * 
     * @param traceBatch 批次信息
     * @return 结果
     */
    @Override
    public int insertTraceBatch(TraceBatch traceBatch)
    {
        return traceBatchMapper.insertTraceBatch(traceBatch);
    }

    /**
     * 修改批次信息
     * 
     * @param traceBatch 批次信息
     * @return 结果
     */
    @Override
    public int updateTraceBatch(TraceBatch traceBatch)
    {
        return traceBatchMapper.updateTraceBatch(traceBatch);
    }

    /**
     * 批量删除批次信息
     * 
     * @param ids 需要删除的批次信息主键
     * @return 结果
     */
    @Override
    public int deleteTraceBatchByIds(Long[] ids)
    {
        return traceBatchMapper.deleteTraceBatchByIds(ids);
    }

    /**
     * 删除批次信息信息
     * 
     * @param id 批次信息主键
     * @return 结果
     */
    @Override
    public int deleteTraceBatchById(Long id)
    {
        return traceBatchMapper.deleteTraceBatchById(id);
    }

    @Override
    public List<RankDTO> selectTraceBatchListRank() {
        return traceBatchMapper.selectTraceBatchListRank();
    }
}
