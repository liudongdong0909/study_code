// package com.study.drools.common.exception.handler;
//
// import com.study.drools.common.enums.RiskRuleStatusEnum;
// import com.study.drools.common.exception.BusinessException;
// import lombok.extern.slf4j.Slf4j;
// import org.springframework.http.HttpStatus;
// import org.springframework.validation.BindException;
// import org.springframework.web.HttpMediaTypeNotSupportedException;
// import org.springframework.web.HttpRequestMethodNotSupportedException;
// import org.springframework.web.bind.annotation.ExceptionHandler;
// import org.springframework.web.bind.annotation.ResponseStatus;
// import org.springframework.web.bind.annotation.RestControllerAdvice;
//
// /**
//  * @author walle
//  */
// @Slf4j
// @RestControllerAdvice
// public class RiskRuleExceptionHandler {
//
//     /**
//      * POST 提交form 数据校验异常 - 400
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(BindException.class)
//     @ResponseStatus(HttpStatus.BAD_REQUEST)
//     public JsonResult handleBindException(BindException exception) {
//         log.error("参数验证失败：", exception);
//         return JsonResult.build(RiskRuleStatusEnum.BAD_REQUEST.code, RiskRuleStatusEnum.BAD_REQUEST.message, null);
//     }
//
//     /**
//      * 不支持当前请求方法 - 405
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
//     @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
//     public JsonResult handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
//         log.error("不支持当前请求方法", exception);
//         return JsonResult.build(RiskRuleStatusEnum.METHOD_NOT_ALLOWED.code, RiskRuleStatusEnum.METHOD_NOT_ALLOWED.message, null);
//     }
//
//     /**
//      * Content-Type 类型错误 - 415
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
//     @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
//     public JsonResult handleHttpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException exception) {
//         log.error("不支持当前 Content-Type 类型：", exception);
//         return JsonResult.build(RiskRuleStatusEnum.UNSUPPORTED_MEDIA_TYPE.code, RiskRuleStatusEnum.UNSUPPORTED_MEDIA_TYPE.message, null);
//     }
//
//     /**
//      * 自定义业务场景异常处理
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(BusinessException.class)
//     @ResponseStatus(HttpStatus.OK)
//     public JsonResult handleBusinessException(BusinessException exception) {
//         log.error("系统业务异常：code= {}, message= {}", exception.getStatus().getCode(), exception.getStatus().getMessage());
//         return JsonResult.build(exception.getStatus().getCode(), exception.getStatus().getMessage(), null);
//     }
//
//     /**
//      * 服务器运行异常 - 500
//      *
//      * @param exception
//      * @return
//      */
//     @ExceptionHandler(Exception.class)
//     @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
//     public JsonResult handleException(Exception exception) {
//         log.error("服务器运行异常，请联系管理员", exception);
//         return JsonResult.errorMsg("服务器运行异常");
//     }
// }
