package com.sb.staging.mapper;


import com.sb.staging.domain.Gicl;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface GiclMapper {
    Gicl selectByPolicyNo(String policyNo);

    List<Gicl> selectAll();
}
