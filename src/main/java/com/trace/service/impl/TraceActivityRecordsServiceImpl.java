package com.trace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.TraceActivityRecordsMapper;
import com.trace.entity.TraceActivityRecords;
import com.trace.service.ITraceActivityRecordsService;

@Service
public class TraceActivityRecordsServiceImpl implements ITraceActivityRecordsService {
    @Autowired
    private TraceActivityRecordsMapper traceActivityRecordsMapper;

    @Override
    public List<TraceActivityRecords> selectTraceActivityRecordsList(TraceActivityRecords traceActivityRecords) {
        return traceActivityRecordsMapper.selectTraceActivityRecordsList(traceActivityRecords);
    }

    @Override
    public int insertTraceActivityRecords(TraceActivityRecords traceActivityRecords) {
        return traceActivityRecordsMapper.insertTraceActivityRecords(traceActivityRecords);
    }
}
