package com.sb.staging.mapper;


import com.sb.common.domain.Agent;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AgentMapper {
    List<Agent> selectAll();
}