package com.study.drools.common.enums;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author walle
 */
public enum RiskRuleStatusEnum implements EnumKeyValue<RiskRuleStatusEnum> {

    // 400 - Bad Reques
    BAD_REQUEST(400, "提交参数不匹配"),

    // 404 - not found
    NOT_FOUND(404, "请求结果未找到"),

    // 405 - Method Not Allowed
    METHOD_NOT_ALLOWED(405, "请求方法类型不匹配"),

    // 415 - Unsupported Media Type
    UNSUPPORTED_MEDIA_TYPE(415, "不支持当前 Content-Type 类型"),

    RISK_RULE_NOT_EXISTS(800, "规则数据不存在"),
    APPROVE_STATUS_STATUS_NOT_EXISTS(801, "提交的规则审批状态不存在"),
    RISK_RULE_CATEGORY_NOT_EXISTS(802, "需要处理的规则分类不存在"),
    OLD_CONSUMER_RISK_RULE_NOT_EXISTS(803, "老客规则分类不存在"),
    NEW_CONSUMER_RISK_RULE_NOT_EXISTS(804, "新客规则分类不存在"),
    DRL_FILE_BUILD_ERROR(805, "drl规则文件编译错误"),
    RISK_RULE_NOT_FOUND_PROGRAM_METHOD(806, "未找到规则对应的程序方法"),
    RISK_EXECUTE_BASE_DTO_NOT_EMPTY(807, "请求风控验证时，基础信息不能为空"),
    RISK_EXECUTE_APP_SOURCE_CHANNEL_NOT_EMPTY(808, "请求风控验证时，app来源下载渠道不能为空"),
    RISK_EXECUTE_USER_ID_NOT_EMPTY(809, "请求风控验证时，登录用户id不能为空"),
    RISK_EXECUTE_APPLY_NUM_NOT_EMPTY(810, "请求风控验证时，审批编号不能为空"),
    TRUST_CHECKR_EMAIL_ERROR(811, "trustcheckr 识别 email 失败"),
    TRUST_CHECKR_PHONE_ERROR(812, "trustcheckr 识别 phone 失败"),
    TRUST_CHECKR_PANCARD_ERROR(813, "trustcheckr 识别 pancard 失败"),

    ;

    public Integer code;

    public String message;

    RiskRuleStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static Optional<RiskRuleStatusEnum> getByCode(Integer code) {
        return Stream.of(RiskRuleStatusEnum.values())
                .filter(value -> value.getCode().equals(code))
                .findFirst();
    }

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getMessage() {
        return this.message;
    }
}
