package com.trace.common.core.domain;

import java.io.Serializable;

public class R<T> extends AjaxResult implements Serializable {
    private static final long serialVersionUID = 1L;

    public R() {
    }

    public R(int code, String msg, T data) {
        super.put(CODE_TAG, code);
        super.put(MSG_TAG, msg);
        if (data != null) {
            super.put(DATA_TAG, data);
        }
    }

    public static <T> R<T> ok() {
        return R.ok("操作成功");
    }

    public static <T> R<T> ok(String msg) {
        return R.ok(msg, null);
    }

    public static <T> R<T> ok(T data) {
        return R.ok("操作成功", data);
    }

    public static <T> R<T> ok(String msg, T data) {
        return new R<>(200, msg, data);
    }

    public static <T> R<T> fail() {
        return R.fail("操作失败");
    }

    public static <T> R<T> fail(String msg) {
        return R.fail(500, msg);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R<>(500, msg, data);
    }

    public static R<Integer> toAjax(int rows) {
        return rows > 0 ? R.ok() : R.fail();
    }
}
