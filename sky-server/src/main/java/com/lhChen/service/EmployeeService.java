package com.lhChen.service;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.result.Result;

public interface EmployeeService {

    public void save(EmployeeDTO employeeDTO);

    public Result login(EmployeeLoginDTO employeeLoginDTO);

}
