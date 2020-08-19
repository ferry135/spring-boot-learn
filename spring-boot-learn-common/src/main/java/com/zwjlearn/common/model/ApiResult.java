package com.zwjlearn.common.model;

import com.zwjlearn.common.constant.ResultStatus;
import lombok.*;

import java.io.Serializable;

/**
 * ApiJSON统一返回格式
 * @param <T>
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ApiResult<T> implements Serializable {
    private String code;
    private String message;
    private String tip;
    private T data;

    public ApiResult(ResultStatus resultStatus,T data){
        this.code = resultStatus.getCode();
        this.message = resultStatus.getMessage();
        this.tip = resultStatus.getTip();
        this.data = data;
    }

    public static <T> ApiResult<T> success() {
        return success(null);
    }
    public static<T> ApiResult<T> success(T data) {
        return success(ResultStatus.OK,data);
    }
    public static<T> ApiResult<T> success(ResultStatus resultStatus, T data) {
        return new ApiResult<>(resultStatus,data);
    }
}
