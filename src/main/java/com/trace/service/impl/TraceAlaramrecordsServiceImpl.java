package com.trace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceAlaramrecordsMapper;
import com.trace.entity.TraceAlaramrecords;
import com.trace.service.ITraceAlaramrecordsService;

@Service
public class TraceAlaramrecordsServiceImpl implements ITraceAlaramrecordsService {
    @Autowired
    private TraceAlaramrecordsMapper traceAlaramrecordsMapper;

    @Override
    public List<TraceAlaramrecords> selectTraceAlaramrecordsList(TraceAlaramrecords traceAlaramrecords) {
        return traceAlaramrecordsMapper.selectTraceAlaramrecordsList(traceAlaramrecords);
    }

    @Override
    public int insertTraceAlaramrecords(TraceAlaramrecords traceAlaramrecords) {
        return traceAlaramrecordsMapper.insertTraceAlaramrecords(traceAlaramrecords);
    }
}
