package com.study.drools.service;

import com.study.drools.mapper.RiskRuleMapper;
import com.study.drools.model.RiskRule;
import com.study.drools.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
@Service
public class RiskRuleService extends BaseService<RiskRule> {

    @Autowired
    private RiskRuleMapper riskRuleMapper;

    public List<RiskRule> queryRiskRuleGroupList(List<Integer> riskRuleCategory) {

        Example example = new Example(RiskRule.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andIn("category", riskRuleCategory);

        return riskRuleMapper.selectByExample(example);
    }


}
