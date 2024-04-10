package com.lhChen.mapper;

import com.lhChen.entity.Employee;
import org.apache.ibatis.annotations.Insert;


import java.util.List;

public interface EmployeeMapper {

    @Insert("insert into employee value (#{id},#{name},#{username},#{password},#{phone},#{sex},#{idNumber},#{status},#{createTime},#{updateTime},#{createUser},#{updateUser});")
    public void save(Employee employee);

    public Employee findEmployeeByName(String name);

    public List<Employee> queryList();

    public Employee findEmployeeById(int id);

    public void update(Employee employee);
}
