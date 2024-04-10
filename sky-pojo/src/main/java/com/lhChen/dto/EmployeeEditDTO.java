package com.lhChen.dto;

import lombok.Data;

@Data
public class EmployeeEditDTO {
    private long id;
    private String idNumber;
    private String name;
    private String phone;
    private String sex;
    private String username;
}
