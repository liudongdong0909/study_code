package com.study.drools.common.drools.group;

import com.study.drools.common.enums.RiskRuleCategoryEnum;
import com.study.drools.common.enums.RiskRuleStatusEnum;
import com.study.drools.common.exception.BusinessException;
import com.study.drools.service.RiskRuleGroup1;
import com.study.drools.service.RiskRuleGroup2;
import com.study.drools.service.RiskRuleGroup3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author walle
 */
@Slf4j
@Component
public class RiskRuleGroupTaskFactory {

    @Autowired
    private RiskRuleGroup1 riskRuleGroup1;

    @Autowired
    private RiskRuleGroup2 riskRuleGroup2;

    @Autowired
    private RiskRuleGroup3 riskRuleGroup3;

    public RiskRuleGroupTaskExecutor getRiskRuleGroupExecutor(RiskRuleCategoryEnum riskRuleCategoryEnum) {

        switch (riskRuleCategoryEnum) {
            case RULE_GROUP_1:
                return riskRuleGroup1;
            case RULE_GROUP_2:
                return riskRuleGroup2;
            case RULE_GROUP_3:
                return riskRuleGroup3;
            default:
                throw new BusinessException(RiskRuleStatusEnum.RISK_RULE_CATEGORY_NOT_EXISTS);
        }
    }
}
