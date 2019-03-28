package com.study.drools.common.drools.handler;

import com.study.drools.model.RiskRule;
import com.study.drools.util.DroolsRuleUtil;
import lombok.extern.slf4j.Slf4j;
import org.kie.api.runtime.KieContainer;
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
public abstract class AbstractLoadAndDeployDrlFileHandler implements LoadDrlFile, DeployDrlFile {

    public KieContainer loadAndDeploy() throws IOException {
        log.info(" 开始执行初始化所有规则文件到内存中 ");

        List<RiskRule> riskRuleList = this.loadDrlFileList();
        KieContainer kieContainer = this.deployDrlFile(riskRuleList);

        log.info(" 执行初始化读取所有规则文件到内存中完成 ");

        DroolsRuleUtil.setKieContainer(kieContainer);
        return kieContainer;
    }


}
