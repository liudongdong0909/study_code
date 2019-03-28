package com.study.drools.common.drools.handler;

import com.study.drools.model.RiskRule;
import org.kie.api.runtime.KieContainer;

import java.io.IOException;
import java.util.List;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
public interface DeployDrlFile {

    KieContainer deployDrlFile(List<RiskRule> ruleList) throws IOException;
}
