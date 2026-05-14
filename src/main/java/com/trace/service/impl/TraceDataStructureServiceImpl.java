package com.trace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceDataStructureMapper;
import com.trace.entity.TraceDataStructure;
import com.trace.service.ITraceDataStructureService;

@Service
public class TraceDataStructureServiceImpl implements ITraceDataStructureService {
    @Autowired
    private TraceDataStructureMapper traceDataStructureMapper;

    @Override
    public List<TraceDataStructure> selectTraceDataStructureList(TraceDataStructure traceDataStructure) {
        return traceDataStructureMapper.selectTraceDataStructureList(traceDataStructure);
    }

    @Override
    public TraceDataStructure selectTraceDataStructureById(Long id) {
        return traceDataStructureMapper.selectTraceDataStructureById(id);
    }

    @Override
    public int insertTraceDataStructure(TraceDataStructure traceDataStructure) {
        return traceDataStructureMapper.insertTraceDataStructure(traceDataStructure);
    }

    @Override
    public int updateTraceDataStructure(TraceDataStructure traceDataStructure) {
        return traceDataStructureMapper.updateTraceDataStructure(traceDataStructure);
    }

    @Override
    public int deleteTraceDataStructureByIds(Long[] ids) {
        return traceDataStructureMapper.deleteTraceDataStructureByIds(ids);
    }
}
