package com.trace.service;

import com.trace.entity.TraceDataStructure;
import java.util.List;

public interface ITraceDataStructureService {
    List<TraceDataStructure> selectTraceDataStructureList(TraceDataStructure traceDataStructure);
    TraceDataStructure selectTraceDataStructureById(Long id);
    int insertTraceDataStructure(TraceDataStructure traceDataStructure);
    int updateTraceDataStructure(TraceDataStructure traceDataStructure);
    int deleteTraceDataStructureByIds(Long[] ids);
}
