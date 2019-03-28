package com.study.drools.common.enums;

/**
 * @author walle
 */
public interface EnumKeyValue<E extends Enum> {

    Integer getCode();

    String getMessage();
}
