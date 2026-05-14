package com.trace.service;

import com.trace.entity.TraceAlaramrecords;
import java.util.List;

public interface ITraceAlaramrecordsService {
    List<TraceAlaramrecords> selectTraceAlaramrecordsList(TraceAlaramrecords traceAlaramrecords);
    int insertTraceAlaramrecords(TraceAlaramrecords traceAlaramrecords);
}
