package com.study.drools.common.drools.execute;

import com.alibaba.fastjson.JSONObject;
import com.study.drools.common.drools.group.RiskRuleGroupTaskFactory;
import com.study.drools.common.enums.AppSourceChannelTypeEnum;
import com.study.drools.common.enums.ApproveStatusEnum;
import com.study.drools.common.enums.RiskRuleCategoryEnum;
import com.study.drools.dto.RiskExecuteRequestDto;
import com.study.drools.dto.RiskExecuteResponseDto;
import com.study.drools.dto.RiskRuleParamsDto;
import com.study.drools.model.RiskRule;
import com.study.drools.util.DroolsRuleUtil;
import lombok.extern.slf4j.Slf4j;
import org.drools.core.base.RuleNameMatchesAgendaFilter;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;

/**
 * @author walle
 */
@Slf4j
@Component
public abstract class AbstractDroolsRuleExecutor implements DroolsRuleExecutor {

    @Autowired
    private RiskRuleGroupTaskFactory riskRuleGroupTaskFactory;

    /**
     * 读取所属当前客户所在的规则分组
     *
     * @return
     */
    public abstract List<RiskRule> loadRuleGroup();

    /**
     * 获取对应组下的所有有效规则
     *
     * @param category
     * @return
     */
    public abstract List<RiskRule> getRisRuleByCategory(Integer category);

    /**
     * 记录规则匹配结果
     *
     * @return
     */
    public List<RiskExecuteResponseDto> recordMatchingResult(List<RiskExecuteResponseDto> riskExecuteResponseDtoList) {
        return riskExecuteResponseDtoList;
    }

    /**
     * 在规则组中处理当前用户的信息
     *
     * @param riskExecuteRequestDto
     * @param consumerRuleGroupList
     * @return
     */
    public List<RiskExecuteResponseDto> processRule(RiskExecuteRequestDto riskExecuteRequestDto, List<RiskRule> consumerRuleGroupList) {
        List<RiskExecuteResponseDto> riskExecuteResponseDtoList = new LinkedList<>();
        // 执行规则组
        KieContainer kieContainer = DroolsRuleUtil.kieContainer;
        KieSession kieSession = kieContainer.newKieSession();
        for (RiskRule groupRule : consumerRuleGroupList) {
            if (AppSourceChannelTypeEnum.GOOGLE_PLAY.getValue().equals(riskExecuteRequestDto.getAppBaseDto().getAppSourceChannel())
                    && RiskRuleCategoryEnum.RULE_GROUP_3.getCode().equals(groupRule.getCategory())) {
                continue;
            }

            // rule_group_1  ---- 6
            RiskRuleCategoryEnum riskRuleCategoryEnum = RiskRuleCategoryEnum.getByCode(groupRule.getCategory()).get();

            kieSession.getAgenda().getAgendaGroup(riskRuleCategoryEnum.getValue()).setFocus();
            log.info("focus 内存中的规则文件: 分组key [{}] 分组描述 [{}]", riskRuleCategoryEnum.getValue(), riskRuleCategoryEnum.getMessage());

            // 获取分组下的所有有效规则
            List<RiskRule> riskRuleList = this.getRisRuleByCategory(riskRuleCategoryEnum.getCode());

            int executeRuleCount = 0;
            boolean breakFlag = false;

            for (RiskRule riskRule : riskRuleList) {
                String riskRuleName = riskRule.getName();
                log.info("开始执行组 [{}] - [{}] 规则 [{}]", riskRuleCategoryEnum.getMessage(), riskRuleCategoryEnum.getValue(), riskRuleName);

                // 获取 kieRuleName对应的规则需要的参数
                RiskRuleParamsDto riskRuleParams = riskRuleGroupTaskFactory
                                                        .getRiskRuleGroupExecutor(riskRuleCategoryEnum)
                                                        .getRiskRuleParams(riskRuleName, riskExecuteRequestDto);

                // 记录规则执行结果
                RiskExecuteResponseDto riskExecuteResponse = new RiskExecuteResponseDto();
                riskExecuteResponse.setRuleId(riskRule.getId());
                riskExecuteResponse.setRuleCategory(riskRuleCategoryEnum.getMessage());
                riskExecuteResponse.setApproveStatus(ApproveStatusEnum.PASS.getCode());
                riskExecuteResponse.setDescription(riskRule.getDescription());
                riskExecuteResponse.setRuleName(riskRuleName);

                FactHandle riskRuleParamsFactHandle = kieSession.insert(riskRuleParams);
                FactHandle riskExecuteResponseFactHandle = kieSession.insert(riskExecuteResponse);

                int ruleFiredCount = kieSession.fireAllRules(new RuleNameMatchesAgendaFilter(riskRuleName));
                executeRuleCount += ruleFiredCount;

                log.info("[{}] - [{}] 执行规则 [{}]校验输出结果：[{}]", riskRuleCategoryEnum.getMessage(), riskRuleCategoryEnum.getValue(),
                        riskRuleName, JSONObject.toJSON(riskExecuteResponse));

                riskExecuteResponseDtoList.add(riskExecuteResponse);

                // 执行结果是拒绝就终止循环
                if (ApproveStatusEnum.REJECT.getCode().equals(riskExecuteResponse.getApproveStatus())) {
                    breakFlag = true;
                }
                kieSession.delete(riskRuleParamsFactHandle);
                kieSession.delete(riskExecuteResponseFactHandle);

                if (breakFlag) {
                    break;
                }
            }
            log.info("[{}] - [{}] 触发了 [{}] 条规则", riskRuleCategoryEnum.getMessage(), riskRuleCategoryEnum.getValue(), executeRuleCount);
            if (breakFlag) {
                break;
            }
        }

        return riskExecuteResponseDtoList;
    }

    @Override
    public List<RiskExecuteResponseDto> execute(RiskExecuteRequestDto riskExecuteRequestDto) {
        List<RiskRule> consumerRuleGroupList = this.loadRuleGroup();
        List<RiskExecuteResponseDto> riskExecuteResponseList = this.processRule(riskExecuteRequestDto, consumerRuleGroupList);
        return this.recordMatchingResult(riskExecuteResponseList);
    }

}
