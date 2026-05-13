package com.trace.dto;

import lombok.Data;

@Data
public class AlaramRule {
    private String code;
    private String operator;
    private String value;
}
