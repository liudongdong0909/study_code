package com.study.drools.common.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 决策状态
 *
 * @author walle
 */
public enum ApproveStatusEnum implements EnumKeyValue<ApproveStatusEnum> {

    REJECT(0, "拒绝"),

    WARN(1, "预警"),

    PASS(2, "通过"),

    ;

    ApproveStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Optional<ApproveStatusEnum> getByCode(Integer code) {
        return Stream.of(ApproveStatusEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst();
    }

    public Integer code;

    public String message;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
