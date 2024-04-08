package com.lhChen.mapper;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.entity.Employee;

public interface EmployeeMapper {

    public void save(Employee employee);

    public Employee findEmployeeByName(String name);

}
