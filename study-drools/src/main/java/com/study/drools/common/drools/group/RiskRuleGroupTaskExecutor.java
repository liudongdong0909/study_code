package com.study.drools.common.drools.group;


import com.study.drools.dto.RiskExecuteRequestDto;
import com.study.drools.dto.RiskRuleParamsDto;

/**
 * @author walle
 */
public interface RiskRuleGroupTaskExecutor {

    RiskRuleParamsDto getRiskRuleParams(String ruleName, RiskExecuteRequestDto riskExecuteRequestDto);
}
