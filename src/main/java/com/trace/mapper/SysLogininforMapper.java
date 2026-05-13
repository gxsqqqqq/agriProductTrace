package com.trace.mapper;

import java.util.List;
import com.trace.entity.SysLogininfor;

public interface SysLogininforMapper
{
    public void insertLogininfor(SysLogininfor logininfor);

    public List<SysLogininfor> selectLogininforList(SysLogininfor logininfor);

    public int deleteLogininforByIds(Long[] infoIds);

    public int cleanLogininfor();
}
