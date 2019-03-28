package com.study.drools.common.drools.execute;

import com.study.drools.common.enums.RiskRuleConsumerEnum;
import com.study.drools.common.enums.RiskRuleStatusEnum;
import com.study.drools.common.exception.BusinessException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author walle
 */
@Slf4j
@Component
public class DroolsRuleExecuteFactory {

    @Autowired
    private DroolsRuleNewConsumerExecutor newConsumerExecutor;

    @Autowired
    private DroolsRuleOldConsumerExecutor oldConsumerExecutor;

    public DroolsRuleExecutor getDroolsRuleExecutor(RiskRuleConsumerEnum consumerType) {
        switch (consumerType) {
            case NEW_CONSUMER:
                return newConsumerExecutor;
            case OLD_CONSUMER:
                return oldConsumerExecutor;
            default:
                throw new BusinessException(RiskRuleStatusEnum.RISK_RULE_CATEGORY_NOT_EXISTS);
        }
    }
}
