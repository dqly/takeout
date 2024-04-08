package com.lhChen.interceptor;

import com.lhChen.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

// 将token转为用户id
//2 在配置类中配置
// 实现HandlerInterceptor
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    public static ThreadLocal<Long> threadLocal=new ThreadLocal<>();


    //目标：把empid传递给EmployeeController
    public static Long empid;
    //重写方法
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        try {
            System.out.println("调用preHandle方法");
            String token = request.getHeader("token");
            Claims claims = JwtUtil.parseJWT("itcast", token);
            empid = Long.parseLong(claims.get("emp_id").toString());
            threadLocal.set(empid);
            //怎么判断登录是否过期
            return true;
        }catch (Exception ex) {
            //4、不通过，响应401状态码
            System.out.println("登录过期");
            response.setStatus(401);
            return false;
        }
    }
}
