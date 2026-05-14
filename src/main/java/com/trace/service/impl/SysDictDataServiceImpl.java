package com.trace.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.trace.mapper.SysDictDataMapper;
import com.trace.common.core.domain.entity.SysDictData;
import com.trace.service.ISysDictDataService;

@Service
public class SysDictDataServiceImpl implements ISysDictDataService {
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    @Override
    public String selectDictLabel(String dictType, String dictValue) {
        SysDictData dictData = new SysDictData();
        dictData.setDictType(dictType);
        dictData.setDictValue(dictValue);
        List<SysDictData> list = sysDictDataMapper.selectDictDataList(dictData);
        if (list != null && !list.isEmpty()) {
            return list.get(0).getDictLabel();
        }
        return dictValue;
    }
    
    private List<SysDictData> selectDictDataList(SysDictData dictData) {
        return sysDictDataMapper.selectDictDataList(dictData);
    }
}
