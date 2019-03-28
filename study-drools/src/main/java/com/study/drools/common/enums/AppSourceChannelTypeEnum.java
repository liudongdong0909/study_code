package com.study.drools.common.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author walle
 */
public enum AppSourceChannelTypeEnum {

    OTHER_SOURCE(0, "other", "其他市场下载App"),
    GOOGLE_PLAY(1, "GooglePlay", "google play 下载"),
    ;

    AppSourceChannelTypeEnum(Integer code, String value, String message) {
        this.code = code;
        this.value = value;
        this.message = message;
    }

    public static Optional<AppSourceChannelTypeEnum> getByCode(Integer code) {
        return Stream.of(AppSourceChannelTypeEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst();
    }

    public Integer code;

    public String value;

    public String message;

    public Integer getCode() {
        return this.code;
    }

    public String getValue() {
        return this.value;
    }

    public String getMessage() {
        return this.message;
    }
}
