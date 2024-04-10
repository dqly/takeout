package com.lhChen.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lhChen.dto.EmployeeDTO;
import com.lhChen.dto.EmployeeEditDTO;
import com.lhChen.dto.EmployeeLoginDTO;
import com.lhChen.dto.EmployeeQueryPageDTO;
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
import java.util.List;
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

    @Override
    public Map<String ,Object> queryPages(EmployeeQueryPageDTO employeeQueryPageDTO){
        PageHelper.startPage(employeeQueryPageDTO.getPage(), employeeQueryPageDTO.getPageSize());
        List<Employee> l=employeeMapper.queryList();
        PageInfo<Employee> pageInfo=new PageInfo<>(l);
        List<Employee> l1=pageInfo.getList();
        Long total=pageInfo.getTotal();
        Map<String ,Object> map=new HashMap();
        map.put("total",total);
        map.put("records",l1);
        return map;
    };


    public void banEmployee(int status,long id){
        Employee employee=new Employee();
        employee.setStatus(status);
        employee.setId(id);
        employeeMapper.update(employee);
    };

    public Employee findEmployeeById(int id){
        Employee employee=employeeMapper.findEmployeeById(id);
        employee.setPassword("******");
        return employee;
    };

    public void edit(EmployeeEditDTO employeeEditDTO){
        Employee employee=new Employee();
        BeanUtils.copyProperties(employeeEditDTO,employee);
        employee.setUpdateTime(LocalDateTime.now());
        employee.setUpdateUser(threadLocal.get());
        employeeMapper.update(employee);
    };
}
