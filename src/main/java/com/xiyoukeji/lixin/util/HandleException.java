package com.xiyoukeji.lixin.util;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ygria on 2018/2/5.
 * SpringMVC的统一异常处理
 */
@Component
public class HandleException implements HandlerExceptionResolver {
    @ResponseBody
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object o, Exception e) {
        Map<String, Object> arr = new HashMap<>();
        if (e instanceof ErrCodeException) {
            arr.put("state", ((ErrCodeException) e).getErrcode());
            arr.put("messgae", e.getMessage());
        } else {
            arr.put("state", "10000");
            arr.put("message", e.getMessage());
        }
        arr.put("time", System.currentTimeMillis());
        return new ModelAndView(new MappingJackson2JsonView(), arr);
    }
}
