package com.study.drools.common.drools.handler;

import com.study.drools.model.RiskRule;

import java.util.List;

/**
 * @author walle
 * @version 1.0
 * @create 2019-03-28
 */
public interface LoadDrlFile {

    List<RiskRule> loadDrlFileList();
}
