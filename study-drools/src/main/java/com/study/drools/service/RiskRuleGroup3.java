package com.study.drools.service;

import com.study.drools.common.drools.group.RiskRuleGroupTaskExecutor;
import com.study.drools.common.enums.RiskRuleStatusEnum;
import com.study.drools.common.exception.BusinessException;
import com.study.drools.dto.RiskExecuteRequestDto;
import com.study.drools.dto.RiskRuleParamsDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-29
 */
@Slf4j
@Service
public class RiskRuleGroup3 implements RiskRuleGroupTaskExecutor {
    @Override
    public RiskRuleParamsDto getRiskRuleParams(String ruleName, RiskExecuteRequestDto riskExecuteRequest) {
        switch (ruleName) {
            case "":
                return this.getRiskRule1(riskExecuteRequest);
            default:
                log.error("规则 [{}] 未找到对应的程序代码", ruleName);
                throw new BusinessException(RiskRuleStatusEnum.RISK_RULE_NOT_FOUND_PROGRAM_METHOD);
        }
    }

    private RiskRuleParamsDto getRiskRule1(RiskExecuteRequestDto riskExecuteRequestDto) {
        // TODO
        return RiskRuleParamsDto.builder().build();
    }

}
