package com.study.excel.service;

import com.study.excel.mapper.BankFlowMapper;
import com.study.excel.model.BankFlow;
import com.study.excel.service.base.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author walle
 */
@Service
public class BankFlowService extends BaseService<BankFlow> {

    @Autowired
    private BankFlowMapper bankFlowMapper;


}
