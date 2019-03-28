package com.study.drools.common.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author walle
 */
public enum RiskRuleCategoryEnum {

    RULE_GROUP_1(1, "规则一", "rule_group_1"),
    RULE_GROUP_2(2, "规则二", "rule_group_2"),
    RULE_GROUP_3(3, "规则三", "rule_group_3"),
    RULE_GROUP_4(4, "规则四", "rule_group_4"),
    RULE_GROUP_5(5, "规则五", "rule_group_5"),
    RULE_GROUP_6(6, "老客规则", "rule_group_6"),
    ;

    public Integer code;

    public String message;

    public String value;

    RiskRuleCategoryEnum(Integer code, String message, String value) {
        this.code = code;
        this.message = message;
        this.value = value;
    }

    public static Optional<RiskRuleCategoryEnum> getByCode(Integer code) {
        return Stream.of(RiskRuleCategoryEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst();
    }

    public static Optional<RiskRuleCategoryEnum> getByMessage(String message) {
        return Stream.of(RiskRuleCategoryEnum.values())
                .filter(value -> value.getMessage().equals(message))
                .findFirst();
    }

    public static Optional<RiskRuleCategoryEnum> getByValue(String value) {
        return Stream.of(RiskRuleCategoryEnum.values())
                .filter(v -> v.getValue().equals(value))
                .findFirst();
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public String getValue() {
        return this.value;
    }
}
