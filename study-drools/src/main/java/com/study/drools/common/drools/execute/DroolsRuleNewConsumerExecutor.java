package com.study.drools.common.drools.execute;

import com.study.drools.common.enums.FunctionStatusEnum;
import com.study.drools.common.enums.RiskRuleCategoryEnum;
import com.study.drools.model.RiskRule;
import com.study.drools.service.RiskRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author walle
 */
@Slf4j
@Component
public class DroolsRuleNewConsumerExecutor extends AbstractDroolsRuleExecutor {

    @Autowired
    private RiskRuleService riskRuleService;

    @Override
    public List<RiskRule> loadRuleGroup() {
        List<Integer> riskRuleCategory = new ArrayList<>(5);
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_1.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_2.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_3.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_4.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_5.getCode());
        return riskRuleService.queryRiskRuleGroupList(riskRuleCategory);
    }

    @Override
    public List<RiskRule> getRisRuleByCategory(Integer category) {
        return riskRuleService.queryListByWhere(RiskRule.builder().status(FunctionStatusEnum.ENABLED.getCode()).build())
                .stream()
                .filter(rule -> StringUtils.isNotBlank(rule.getContent()))
                .collect(Collectors.toList());
    }
}
