package com.lhChen.result;

import lombok.Data;

@Data
public class ResultEnum {

    private final int code;
    private final String msg;

    ResultEnum(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    public static final ResultEnum SUCCESS=new ResultEnum(200,"success");
    public static final ResultEnum FAIL=new ResultEnum(500,"fail");
    public static final ResultEnum NOTLOGIN=new ResultEnum(401,"用户未登录");
    public static final ResultEnum ERRORPASSWORD=new ResultEnum(400,"密码错误");
    public static final ResultEnum USERNOTEXIST=new ResultEnum(400,"用户名不存在");
}
