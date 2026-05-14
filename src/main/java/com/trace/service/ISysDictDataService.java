package com.trace.service;

import com.trace.common.core.domain.R;
import java.util.List;

public interface ISysDictDataService {
    String selectDictLabel(String dictType, String dictValue);
}
