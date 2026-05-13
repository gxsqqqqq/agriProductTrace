package com.trace.common.core.domain;

public class R<T> extends HashMap<String, Object> {
    private static final long serialVersionUID = 1L;

    public static final String CODE_TAG = "code";
    public static final String MSG_TAG = "msg";
    public static final String DATA_TAG = "data";

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
        return R.fail(msg, null);
    }

    public static <T> R<T> fail(int code, String msg) {
        return new R<>(code, msg, null);
    }

    public static <T> R<T> fail(String msg, T data) {
        return new R<>(500, msg, data);
    }

    @Override
    public R<T> put(String key, Object value) {
        super.put(key, value);
        return this;
    }
}
