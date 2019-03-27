package com.study.excel.controller;

import com.study.excel.model.BankFlow;
import com.study.excel.service.BankFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @author walle
 */
@RestController
@RequestMapping("/excel")
public class ExcelController {

    @Autowired
    private BankFlowService bankFlowService;

    @GetMapping("/list")
    public List<BankFlow> queryList(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate) {
        BankFlow bankFlow = new BankFlow();
        bankFlow.setStartDate(startDate);
        return bankFlowService.queryListByWhere(bankFlow);
    }

    @PostMapping("/save")
    public BankFlow save(@RequestBody BankFlow bankFlow){
        return bankFlow;
    }
}
