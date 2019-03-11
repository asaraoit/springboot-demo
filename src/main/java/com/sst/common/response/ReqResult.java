package com.sst.common.response;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName ReqResult
 * @Description TODO
 * @Author Asarao
 * @Date 2018/11/12 16:56
 * @Version 1.0
 */
@Data
public class ReqResult implements Serializable {

    private static final long serialVersionUID = 1L;

    private String message;

    private Integer code;

    private Object data;

    public static ReqResult POST_PARAM_ERROR = new ReqResult(400, "错误的参数", null);

    public static ReqResult EXCEPTION_ERROR = new ReqResult(500, "系统异常", null);

    public static ReqResult COMMON_SUCCESS = new ReqResult(200, "成功", null);

    public static ReqResult COMMON_ERROR = new ReqResult(400, "失败", null);

    public static ReqResult   AUTH_FAILED = new ReqResult(401, "会话验证失败", null);

    private ReqResult(Integer code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ReqResult setData(Object data) {
        return new ReqResult(this.code, this.message, data);
    }

    public ReqResult setMessage(String message){
        return new ReqResult(this.code,message,this.data);
    }
}
