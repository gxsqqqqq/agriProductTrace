package com.trace.common.utils.poi;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ExcelUtil<T> {

    private Class<T> clazz;

    public ExcelUtil(Class<T> clazz) {
        this.clazz = clazz;
    }

    public ExcelUtil() {
    }

    public void exportExcel(HttpServletResponse response, List<T> list, String sheetName) {
        // Basic Excel export implementation placeholder
    }

    public void exportExcel(List<T> list, String sheetName, Class<T> clazz) {
        // Basic Excel export implementation placeholder
    }

    public static <T> List<T> importExcel(String filePath, Class<T> clazz) {
        // Basic Excel import implementation placeholder
        return null;
    }
}
