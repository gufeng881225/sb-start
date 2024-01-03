package com.sb.staging.service.impl;

import com.sb.staging.domain.Gicl;
import com.sb.staging.mapper.GiclMapper;
import com.sb.staging.service.GiclService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GiclServiceImpl implements GiclService {

    @Autowired
    private GiclMapper userMapper;

    @Override
    public Gicl selectByPolicyNo(String policyNo) {
        return userMapper.selectByPolicyNo(policyNo);
    }

    @Override
    public List<Gicl> selectAll() {
        List<Gicl> users = userMapper.selectAll();
        return users;
    }
}
