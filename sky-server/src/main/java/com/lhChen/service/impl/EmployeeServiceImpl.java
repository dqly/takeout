package com.lhChen.service.impl;

import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.entity.Employee;
import com.lhChen.mapper.EmployeeMapper;
import com.lhChen.properities.JwtProperties;
import com.lhChen.result.Result;
import com.lhChen.result.ResultEnum;
import com.lhChen.service.EmployeeService;
import com.lhChen.utils.JwtUtil;
import com.lhChen.vo.EmployeeLoginVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import static com.lhChen.interceptor.JwtTokenAdminInterceptor.threadLocal;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    @Autowired
    EmployeeMapper employeeMapper;
    @Autowired
    private JwtProperties jwtProperties;

    @Override
    public void save(EmployeeDTO employeeDTO) {
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeDTO,employee);
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employee.setStatus(1);
        employee.setCreateTime(LocalDateTime.now());
        employee.setUpdateTime(LocalDateTime.now());
        employee.setCreateUser(threadLocal.get());
        employee.setUpdateUser(threadLocal.get());
        employeeMapper.save(employee);
    }

    @Override
    public Result login(EmployeeLoginDTO employeeLoginDTO) {
       Employee employee=employeeMapper.findEmployeeByName(employeeLoginDTO.getUsername());
       if(employee.getUsername()!=null){
           // 对密码进行编码
           String password = DigestUtils.md5DigestAsHex(employeeLoginDTO.getPassword().getBytes());
           if(employee.getPassword().equals(password)){
               Map<String, Object> claims = new HashMap<>();
               claims.put("emp_id", employee.getId());
               String token = JwtUtil.createJWT("itcast",7200000,claims);

               EmployeeLoginVO employeeLoginVO = new EmployeeLoginVO();
               BeanUtils.copyProperties(employee,employeeLoginVO);
               employeeLoginVO.setToken(token);

               return new Result(ResultEnum.SUCCESS,employeeLoginVO);
           }else{
               return new Result(ResultEnum.ERRORPASSWORD,null);
           }
       }else{
           return new Result(ResultEnum.USERNOTEXIST,null);
       }
    }


}
