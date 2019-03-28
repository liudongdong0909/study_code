package com.study.drools.common.runner;

import com.study.drools.common.drools.handler.DateBaseLoadAndDeployDrlFileHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
@Component
public class RiskRuleDrlFileInitRunner implements CommandLineRunner {

    @Autowired
    private DateBaseLoadAndDeployDrlFileHandler loadAndDeployDrlFileHandler;

    @Override
    public void run(String... args) throws Exception {
        loadAndDeployDrlFileHandler.loadAndDeploy();
    }
}
