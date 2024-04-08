package com.lhChen.controller;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.interceptor.JwtTokenAdminInterceptor;
import com.lhChen.result.Result;
import com.lhChen.result.ResultEnum;
import com.lhChen.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("employee")
@RestController
@Slf4j
public class EmployeeController {


    @Autowired
    EmployeeService employeeService;

    // 去掉 用post请求/admin/employee，报404
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
}
