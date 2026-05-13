package com.trace.common.core.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.trace.common.core.page.TableDataInfo;
import com.trace.common.core.domain.R;

import java.util.List;

public class BaseController {

    protected void startPage() {
        PageHelper.startPage(1, 10);
    }

    protected void startPage(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
    }

    protected <T> TableDataInfo getDataTable(List<T> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(200);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    protected R<Void> success() {
        return R.ok();
    }

    protected <T> R<T> success(T data) {
        return R.ok(data);
    }

    protected R<Void> error() {
        return R.fail();
    }

    protected R<Void> error(String msg) {
        return R.fail(msg);
    }

    protected R<Void> error(int code, String msg) {
        return R.fail(code, msg);
    }
}
