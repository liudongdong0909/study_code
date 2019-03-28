package com.study.drools.service;

import com.study.drools.common.drools.execute.DroolsRuleExecuteFactory;
import com.study.drools.common.enums.RiskRuleConsumerEnum;
import com.study.drools.dto.RiskExecuteRequestDto;
import com.study.drools.dto.RiskExecuteResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-29
 */
@Slf4j
@Service
public class RiskExecuteService {

    @Autowired
    private DroolsRuleExecuteFactory droolsRuleExecuteFactory;

    public List<RiskExecuteResponseDto> executeRule(RiskExecuteRequestDto riskExecuteRequest) {
        RiskRuleConsumerEnum consumerType = this.checkConsumerType(riskExecuteRequest);
        return droolsRuleExecuteFactory.getDroolsRuleExecutor(consumerType).execute(riskExecuteRequest);
    }

    private RiskRuleConsumerEnum checkConsumerType(RiskExecuteRequestDto riskExecuteRequest) {
        boolean isNewConsumer = true;
        // TODO 判断新老客户 xxxxxxx
        return isNewConsumer ? RiskRuleConsumerEnum.NEW_CONSUMER : RiskRuleConsumerEnum.OLD_CONSUMER;
    }
}
