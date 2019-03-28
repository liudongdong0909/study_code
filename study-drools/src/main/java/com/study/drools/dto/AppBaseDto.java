package com.study.drools.dto;

import lombok.*;

/**
 * @author walle
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AppBaseDto {

    /**
     * app 渠道来源
     */
    private String appSourceChannel;

    /**
     * 订单编号
     */
    private String orderNum;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户手机号
     */
    private String phone;
}
