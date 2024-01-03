package com.sb.staging.service;


import com.sb.staging.domain.Gicl;

import java.util.List;

public interface GiclService {

    Gicl selectByPolicyNo(String policyNo);

    List<Gicl> selectAll();
}

