package com.trace.common.core.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AjaxResult extends HashMap<String, Object> implements Serializable {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

    public AjaxResult() {
    }

    public static AjaxResult success() {
        return AjaxResult.success("操作成功");
    }

    public static AjaxResult success(Object data) {
        return AjaxResult.success("操作成功", data);
    }

    public static AjaxResult success(String msg) {
        return AjaxResult.success(msg, null);
    }

    public static AjaxResult success(String msg, Object data) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put(CODE_TAG, 200);
        ajaxResult.put(MSG_TAG, msg);
        if (data != null) {
            ajaxResult.put(DATA_TAG, data);
        }
        return ajaxResult;
    }

    public static AjaxResult error() {
        return AjaxResult.error("操作失败");
    }

    public static AjaxResult error(String msg) {
        return AjaxResult.error(500, msg);
    }

    public static AjaxResult error(int code, String msg) {
        AjaxResult ajaxResult = new AjaxResult();
        ajaxResult.put(CODE_TAG, code);
        ajaxResult.put(MSG_TAG, msg);
        return ajaxResult;
    }

    public static AjaxResult toAjax(int rows) {
        return rows > 0 ? AjaxResult.success() : AjaxResult.error();
    }
}
