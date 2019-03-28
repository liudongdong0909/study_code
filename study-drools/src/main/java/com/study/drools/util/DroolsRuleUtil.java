package com.study.drools.util;

import org.kie.api.runtime.KieContainer;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
public class DroolsRuleUtil {

    public static KieContainer kieContainer;

    public static void setKieContainer(KieContainer kieContainer){
        DroolsRuleUtil.kieContainer = kieContainer;
    }
}
