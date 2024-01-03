package com.sb.staging.service;


import com.sb.common.domain.Agent;
import com.sb.staging.domain.Gicl;

import java.util.List;

public interface AgentService {

    List<Agent> selectAll();
}

