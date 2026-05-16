package com.trace.service;

import org.springframework.stereotype.Service;

@Service("ss")
public class PermissionService {
    public boolean hasPermi(String permission) {
        return true;
    }
}
