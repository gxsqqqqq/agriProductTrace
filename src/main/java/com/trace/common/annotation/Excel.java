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

    enum ColumnType {
        TEXT,
        NUMERIC,
        IMAGE
    }

    ColumnType cellType() default ColumnType.TEXT;
}
