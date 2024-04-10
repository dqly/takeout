package com.lhChen.service;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeEditDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.dto.EmployeeQueryPageDTO;
import com.lhChen.entity.Employee;
import com.lhChen.result.Result;

import java.util.List;
import java.util.Map;

public interface EmployeeService {

    public void save(EmployeeDTO employeeDTO);

    public Result login(EmployeeLoginDTO employeeLoginDTO);

    public Map<String ,Object> queryPages(EmployeeQueryPageDTO employeeQueryPageDTO);

    public void banEmployee(int status,long id);

    public Employee findEmployeeById(int id);

    public void edit(EmployeeEditDTO employeeEditDTO);

}
