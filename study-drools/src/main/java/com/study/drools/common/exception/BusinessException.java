package com.study.drools.common.exception;


import com.study.drools.common.enums.EnumKeyValue;

/**
 * @author walle
 */
public class BusinessException extends BaseException{

    public BusinessException(EnumKeyValue<?> status) {
        super(status);
    }

    public BusinessException(EnumKeyValue<?> status, Throwable cause) {
        super(status, cause);
    }
}