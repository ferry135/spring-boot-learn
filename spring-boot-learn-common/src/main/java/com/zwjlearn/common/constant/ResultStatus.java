package com.zwjlearn.common.constant;

public enum ResultStatus {
    // success 一切正常
    OK("00000", "操作成功","操作成功"),

    // Internal Error 内部系统错误，业务逻辑，程序健壮性方面
    INTERNAL_ERROR("B0001","系统执行出错","系统执行出错"),

    // external error 外部错误，调用第三方服务失败，RPC错误，数据库错误
    EXTERNAL_ERROR("C0001","调用第三方服务出错","系统执行出错"),

    DB_ERROR("C0300","数据库服务出错","系统执行出错");
    /**
     * 状态码
     */
    private final String code;
    /**
     * 消息，用于排错
     */
    private final String message;
    /**
     * 提示信息，用于给用户提供友好提示
     */
    private final String tip;

    ResultStatus(String code, String message, String tip) {
        this.code = code;
        this.message = message;
        this.tip = tip;
    }

    public String getCode(){
        return this.code;
    }

    public String getMessage() {
        return message;
    }

    public String getTip() {
        return tip;
    }
}
