package com.zhu.springcloud.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 前端返回值类
 * @author zhuchong
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult<T> {
    private  Integer code;

    private String message;

    private T data;

    public CommonResult(Integer code, String message){
        this(code, message,null);
    }
}
