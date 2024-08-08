package com.mindhub.homebanking.service.implentacion;


import com.mindhub.homebanking.service.UtilService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class UtilServiceImp implements UtilService {

    @Override
    @Transactional
    public Map<String, Object> errorResult(BindingResult result) {
        Map<String, Object> response = new HashMap<>();
        if (result.hasErrors()) {
            List<String> errors = result.getFieldErrors()
                    .stream()
                    .map(err -> "el campo '" + err.getField() + "'" + err.getDefaultMessage())
                    .collect(Collectors.toList());
            response.put("errors", errors);
        }
        return response;
    }
}
