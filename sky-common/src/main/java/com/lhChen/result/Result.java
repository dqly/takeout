package com.lhChen.result;

import lombok.Data;

@Data
public class Result<T> {

    private int code;
    private String msg;
    private T data;

    public Result(ResultEnum resultEnum,T data){
        this.code=resultEnum.getCode();
        this.msg=resultEnum.getMsg();
        this.data=data;
    }
}
