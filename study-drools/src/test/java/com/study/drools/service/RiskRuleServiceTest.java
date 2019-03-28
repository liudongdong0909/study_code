package com.study.drools.service;

import com.alibaba.fastjson.JSONObject;
import com.study.drools.StudyDroolsApplicationTests;
import com.study.drools.common.enums.RiskRuleCategoryEnum;
import com.study.drools.model.RiskRule;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-29
 */
public class RiskRuleServiceTest extends StudyDroolsApplicationTests {

    @Autowired
    private RiskRuleService riskRuleService;

    @Test
    public void queryRiskRuleGroupList() {
        List<Integer> riskRuleCategory = new ArrayList<>(5);
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_1.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_2.getCode());
        riskRuleCategory.add(RiskRuleCategoryEnum.RULE_GROUP_3.getCode());

        riskRuleService.queryRiskRuleGroupList(riskRuleCategory)
                .forEach(riskRule ->
                        System.out.println(JSONObject.toJSONString(riskRule))
                );
    }
}