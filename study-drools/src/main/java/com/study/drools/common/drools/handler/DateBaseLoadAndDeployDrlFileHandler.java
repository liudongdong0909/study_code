package com.study.drools.common.drools.handler;

import com.alibaba.fastjson.JSONObject;
import com.google.common.base.Strings;
import com.study.drools.common.enums.FunctionStatusEnum;
import com.study.drools.model.RiskRule;
import com.study.drools.service.RiskRuleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.kie.api.KieServices;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.KieRepository;
import org.kie.api.runtime.KieContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
@Slf4j
@Component
public class DateBaseLoadAndDeployDrlFileHandler extends AbstractLoadAndDeployDrlFileHandler {

    @Autowired
    private RiskRuleService riskRuleService;

    @Override
    public List<RiskRule> loadDrlFileList() {
        log.info(" 开始从数据库中读取所有规则文件 ");

        RiskRule riskRule = RiskRule.builder()
                .status(FunctionStatusEnum.ENABLED.getCode())
                .build();

        List<RiskRule> riskRuleList = riskRuleService.queryListByWhere(riskRule);
        riskRuleList.forEach(riskRule1 -> log.info(" 读取规则：【{}】", JSONObject.toJSONString(riskRule)));

        log.info(" 总计加载【{}】个已启动的规则文件 ", riskRuleList.size());
        log.info(" 完成从数据库中读取所有规则文件 ");
        return riskRuleList;
    }


    @Override
    public KieContainer deployDrlFile(List<RiskRule> ruleList) throws IOException {

        KieServices kieServices = KieServices.Factory.get();
        KieRepository kieRepository = kieServices.getRepository();
        KieFileSystem kieFileSystem = kieServices.newKieFileSystem();

        ruleList.stream()
                .filter(rule -> StringUtils.isNoneBlank(rule.getContent()))
                .forEach(rule -> {
                    String drlContent = rule.getContent();
                    // 1. .drl文件中的 rule "#{rule_name}", {rule_name} 需要替换为 risk_rule_ + level，确保有序， risk_rule_88
                    // 2. .drl文件中的 salience "#{rule_salience}", #{rule_salience} 需要替换为 level，确保有序， 88
                    // 3. .drl文件中的 agenda-group "#{rule_agenda_group}", #{rule_agenda_group}需要替换为 rule_group_ + category. rule_group_88
                    // 4. .drl文件中的 riskExecuteResponse.setApproveStatus(#{rule_approve_status});， #{rule_approve_status} 需要替换为 rule.getApproveStatus 的值
                    drlContent = StringUtils.replacePattern(drlContent, "#\\{rule_name\\}", "risk_rule_" + rule.getLevel());
                    drlContent = StringUtils.replacePattern(drlContent, "#\\{rule_agenda_group\\}", "rule_group_" + rule.getCategory());
                    drlContent = StringUtils.replacePattern(drlContent, "#\\{rule_salience\\}", rule.getLevel().toString());
                    drlContent = StringUtils.replacePattern(drlContent, "#\\{rule_approve_status\\}", rule.getApproveStatus().toString());

                    kieFileSystem.write("src/main/resources/rules/" + rule.getName() + ".drl", drlContent);
                });

        return kieServices.newKieContainer(kieRepository.getDefaultReleaseId());
    }

}
