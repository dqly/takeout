package com.lhChen.dto;

import lombok.Data;

@Data
public class EmployeeQueryPageDTO {
    private String name;
    private int page;
    private int pageSize;
}
