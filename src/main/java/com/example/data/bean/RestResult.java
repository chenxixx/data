package com.example.data.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel
public class RestResult<T> {
    @ApiModelProperty(value = "返回标识，0成功1失败")
    private String code;
    @ApiModelProperty(value = "返回信息")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RestResult success(T data){
        this.setCode("0");
        this.setMessage("success");
        this.setData(data);
        return this;
    }

    public RestResult success(String message,T data){
        this.setCode("0");
        this.setMessage(message);
        this.setData(data);
        return this;
    }

    public RestResult fail(String message){
        this.setCode("0");
        this.setMessage(message);
        return this;
    }
}
