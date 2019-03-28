package com.study.drools.dto;

import lombok.*;

import java.math.BigDecimal;

/**
 * 具体参数按照具体规则定义，继承此类
 *
 * @author walle
 */

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RiskRuleParamsDto extends RiskExecuteRequestDto {

    /**
     * 查询规则约定结果
     */
    private boolean queryResult;

    /**
     * 数量对象  取数量为一个对象
     */
    private int countNum;

    /**
     * 客户职业
     */
    private String profession;

    /**
     * 客户家庭地址
     */
    private String homeState;

    /**
     * 客户公司地址
     */
    private String companyState;

    /**
     * 客户月收入
     */
    private BigDecimal salary;

    /**
     * 申请时间
     */
    private String applyTime;

    /**
     * 账户数量
     */
    private int accountNumber;

}
