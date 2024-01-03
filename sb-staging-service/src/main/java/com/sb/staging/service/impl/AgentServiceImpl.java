package com.sb.staging.service.impl;

import com.sb.common.domain.Agent;
import com.sb.staging.mapper.AgentMapper;
import com.sb.staging.service.AgentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgentServiceImpl implements AgentService {

    @Autowired
    private AgentMapper agentMapper;


    @Override
    public List<Agent> selectAll() {
        return agentMapper.selectAll();
    }
}
