package com.trace.common.xss;

import org.apache.commons.lang3.StringUtils;

public class Xss {
    private static final String HTML_TAG = "<[^>]*>";

    public static String filter(String value) {
        if (StringUtils.isBlank(value)) {
            return value;
        }
        return value.replaceAll(HTML_TAG, "");
    }

    public static boolean isValid(String value) {
        if (StringUtils.isBlank(value)) {
            return true;
        }
        return !value.matches(HTML_TAG);
    }
}
