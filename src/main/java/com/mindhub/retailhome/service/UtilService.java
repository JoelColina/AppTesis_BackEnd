package com.mindhub.retailhome.service;

import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Map;

@Service
public interface UtilService {
    Map<String, Object> errorResult(BindingResult result );
}
