package com.trace.service;

import com.trace.entity.TraceActivityRecords;
import java.util.List;

public interface ITraceActivityRecordsService {
    List<TraceActivityRecords> selectTraceActivityRecordsList(TraceActivityRecords traceActivityRecords);
    int insertTraceActivityRecords(TraceActivityRecords traceActivityRecords);
}
