package com.study.drools.common.drools.execute;


import com.study.drools.dto.RiskExecuteRequestDto;
import com.study.drools.dto.RiskExecuteResponseDto;

import java.util.List;

/**
 * @author walle
 */
public interface DroolsRuleExecutor {

    List<RiskExecuteResponseDto> execute(RiskExecuteRequestDto riskExecuteRequestDto);
}
