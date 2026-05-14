package com.trace.common.annotation;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Excel {
    String name() default "";
    String readConverterExp() default "";
    String separator() default ",";
    int maxCellLength() default -1;
    int width() default 0;
    String dateFormat() default "";
    String suffix() default "";

    enum ColumnType {
        TEXT,
        NUMERIC,
        IMAGE
    }

    ColumnType cellType() default ColumnType.TEXT;
}
