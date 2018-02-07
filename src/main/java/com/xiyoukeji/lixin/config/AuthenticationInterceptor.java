package com.xiyoukeji.lixin.config;


import com.xiyoukeji.lixin.annotation.Access;
import com.xiyoukeji.lixin.domain.Admin;
import com.xiyoukeji.lixin.service.AdminService;
import com.xiyoukeji.lixin.type.AdminRoleEnum;
import com.xiyoukeji.lixin.util.ApplicationContextTool;
import com.xiyoukeji.lixin.util.ErrCodeException;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * Created by ygria on 2018/1/6.
 */
public class AuthenticationInterceptor extends HandlerInterceptorAdapter {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 将handler强转为HandlerMethod, 前面已经证实这个handler就是HandlerMethod
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        // 从方法处理器中获取出要调用的方法
        Method method = handlerMethod.getMethod();
        // 获取出方法上的Access注解
        Access access = method.getAnnotation(Access.class);
        if (access == null) {
            // 如果注解为null, 说明不需要拦截, 直接放过
            return true;
        }
        if (access.value()!=null) {
            // 如果权限配置不为空, 则取出配置值
           Admin admin = ApplicationContextTool.applicationContext.getBean(AdminService.class).getCurAdmin(request);

            if(admin==null){
                throw new ErrCodeException(10003,"管理员没有登录");
            }
            if(access.value()== AdminRoleEnum.SUPER||admin.getAdmin_role()!=AdminRoleEnum.SUPER){
                throw new ErrCodeException(10002,"当前用户无权限");
            }

        }
        // 拦截之后应该返回公共结果, 这里没做处理
        return true;
    }

}
