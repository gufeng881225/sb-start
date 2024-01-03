package com.sb.staging.service.impl;


import com.sb.staging.service.AnimalService;
import org.springframework.stereotype.Service;

@Service
public class AnimalServiceImpl implements AnimalService {

    @Override
    public String getCategory(String str) {
        return str;
    }
}
