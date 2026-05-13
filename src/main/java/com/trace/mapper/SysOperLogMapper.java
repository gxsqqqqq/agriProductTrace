package com.trace.mapper;

import java.util.List;
import com.trace.entity.SysOperLog;

public interface SysOperLogMapper
{
    public void insertOperlog(SysOperLog operLog);

    public List<SysOperLog> selectOperLogList(SysOperLog operLog);

    public int deleteOperLogByIds(Long[] operIds);

    public SysOperLog selectOperLogById(Long operId);

    public void cleanOperLog();
}
