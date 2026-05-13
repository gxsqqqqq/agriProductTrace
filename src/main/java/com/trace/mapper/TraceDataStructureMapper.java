package com.trace.mapper;

import java.util.List;
import com.trace.entity.TraceDataStructure;

/**
 * 数据结构Mapper接口
 * 
 * 
 * @date 2024-10-07
 */
public interface TraceDataStructureMapper 
{
    /**
     * 查询数据结构
     * 
     * @param id 数据结构主键
     * @return 数据结构
     */
    public TraceDataStructure selectTraceDataStructureById(Long id);

    /**
     * 查询数据结构列表
     * 
     * @param traceDataStructure 数据结构
     * @return 数据结构集合
     */
    public List<TraceDataStructure> selectTraceDataStructureList(TraceDataStructure traceDataStructure);

    /**
     * 新增数据结构
     * 
     * @param traceDataStructure 数据结构
     * @return 结果
     */
    public int insertTraceDataStructure(TraceDataStructure traceDataStructure);

    /**
     * 修改数据结构
     * 
     * @param traceDataStructure 数据结构
     * @return 结果
     */
    public int updateTraceDataStructure(TraceDataStructure traceDataStructure);

    /**
     * 删除数据结构
     * 
     * @param id 数据结构主键
     * @return 结果
     */
    public int deleteTraceDataStructureById(Long id);

    /**
     * 批量删除数据结构
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteTraceDataStructureByIds(Long[] ids);
}
