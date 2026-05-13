package com.trace.mapper;

import java.util.List;
import com.trace.entity.TraceBatch;
import com.trace.dto.RankDTO;

/**
 * 批次信息Mapper接口
 * 
 * 
 * @date 2024-10-13
 */
public interface TraceBatchMapper 
{
    /**
     * 查询批次信息
     * 
     * @param id 批次信息主键
     * @return 批次信息
     */
    public TraceBatch selectTraceBatchById(Long id);

    /**
     * 查询批次信息列表
     * 
     * @param traceBatch 批次信息
     * @return 批次信息集合
     */
    public List<TraceBatch> selectTraceBatchList(TraceBatch traceBatch);

    /**
     * 新增批次信息
     * 
     * @param traceBatch 批次信息
     * @return 结果
     */
    public int insertTraceBatch(TraceBatch traceBatch);

    /**
     * 修改批次信息
     * 
     * @param traceBatch 批次信息
     * @return 结果
     */
    public int updateTraceBatch(TraceBatch traceBatch);

    /**
     * 删除批次信息
     * 
     * @param id 批次信息主键
     * @return 结果
     */
    public int deleteTraceBatchById(Long id);

    /**
     * 批量删除批次信息
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceBatchByIds(Long[] ids);

    List<RankDTO> selectTraceBatchListRank();
}
