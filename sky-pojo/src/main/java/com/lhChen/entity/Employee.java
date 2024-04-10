package com.lhChen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Employee {
    private long id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private String sex;
    private String idNumber;
    private int status;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private long createUser;
    private long updateUser;
}
