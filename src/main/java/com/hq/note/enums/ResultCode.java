package com.hq.note.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 请求返回状态枚举
 *
 * @author HQ
 **/
@Getter
@AllArgsConstructor
public enum ResultCode {

    // 请求成功
    SUCCESS(200, "请求成功"),
    // 无权访问
    UNAUTHORIZED(401, "无权访问"),
    // 请求异常
    ERROR(500, "系统异常");

    private final int code;
    private final String value;

}
