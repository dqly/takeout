package com.lhChen.controller;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeEditDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.dto.EmployeeQueryPageDTO;
import com.lhChen.entity.Employee;
import com.lhChen.result.Result;
import com.lhChen.result.ResultEnum;
import com.lhChen.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RequestMapping("employee")
@RestController
@Slf4j
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping
    public Result add(@RequestBody EmployeeDTO employeeDTO){
        try {
            employeeService.save(employeeDTO);
        }catch (Exception e){
            System.out.println(e.getMessage());
            return new Result(ResultEnum.FAIL,null);
        }
        return new Result(ResultEnum.SUCCESS,null);
    }

    @PostMapping("login")
    public Result login(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info(employeeLoginDTO.getUsername()+"员工登录");
        Result r= employeeService.login(employeeLoginDTO);
        return r;
    }

    @GetMapping("page")
    public Result queryPages(EmployeeQueryPageDTO employeeQueryPageDTO){
        Map<String ,Object> map=employeeService.queryPages(employeeQueryPageDTO);
        return new Result<Map>(ResultEnum.SUCCESS,map);
    }

    @PostMapping("status/{status}")
    public Result banEmployee(@PathVariable int status,@RequestParam long id){
        System.out.println(status+"     "+id);
        employeeService.banEmployee(status,id);
        return new Result(ResultEnum.SUCCESS,null);
    }

    @GetMapping("{id}")
    public Result findEmployeeById(@PathVariable int id){
        Employee employee =employeeService.findEmployeeById(id);
        return new Result(ResultEnum.SUCCESS,employee);
    }

    @PutMapping
    public Result edit(@RequestBody EmployeeEditDTO employeeEditDTO){
        System.out.println(employeeEditDTO);
        employeeService.edit(employeeEditDTO);
        return new Result(ResultEnum.SUCCESS,null);
    }
}
