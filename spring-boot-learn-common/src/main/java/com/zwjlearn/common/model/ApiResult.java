package com.zwjlearn.common.model;

import lombok.*;

import java.io.Serializable;

/**
 * ApiJSON统一返回格式
 * @param <T>
 * @version 1.0
 */
@Getter
@Setter
@Builder
public class ApiResult<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public ApiResult() {
    }

    public ApiResult(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ApiResult<T> success() {
        return success(null);
    }
    public static<T> ApiResult<T> success(T data) {
        return success("操作成功",data);
    }
    public static<T> ApiResult<T> success(String message, T data) {
        return new ApiResult<>(200,message,data);
    }

    public static<T> ApiResult<T> failure(String message, T data) {
        return new ApiResult<>(500,message,data);
    }
    public static<T> ApiResult<T> failure(T data) {
        return new ApiResult<>(500,"操作失败",data);
    }
    public static<T> ApiResult<T> failure(String message) {
        return failure(message,null);
    }
    public static <T> ApiResult<T> failure() {
        return failure(null);
    }
}
