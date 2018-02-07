package com.xiyoukeji.lixin.controller;

import com.xiyoukeji.lixin.annotation.Access;
import com.xiyoukeji.lixin.service.AdminService;
import com.xiyoukeji.lixin.service.LoginService;
import com.xiyoukeji.lixin.type.AdminRoleEnum;
import com.xiyoukeji.lixin.util.MapTool;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by ygria on 2018/2/5.
 * 管理员控制
 */
@RestController
@RequestMapping("/admin")
public class AdminController {
    @Resource
    AdminService adminService;
    @Resource
    LoginService loginService;

    /**
     * 管理员用户名、密码登录
     *
     * @param username
     * @param password
     * @return
     */
    @RequestMapping("/login")
    public Map login(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        return adminService.userLogin(username, password, request, response);
    }

    @Access(value = AdminRoleEnum.SUPER)
    @RequestMapping("/save_or_update")
    public Map saveAdmin(Long id, String username, String password) {
        return adminService.saveOrUpdateAdmin(id, username, password);

    }

    @Access(value = AdminRoleEnum.SUPER)
    @RequestMapping("/list")
    public Map listAmdin(Integer pageNum, Integer rowNum, String username, Boolean disabled) {

        return adminService.adminList(pageNum, rowNum, username, disabled);

    }
    @Access(value = AdminRoleEnum.SUPER)
    @RequestMapping("disabled")
    public Map disableAdmin(Long id) {
        return adminService.disabledAdmin(id);
    }

    @RequestMapping("get")
    public Map getCurAdmin(HttpServletRequest request) {
        return MapTool.Mapok().put("data", adminService.getCurAdmin(request));
    }

    @RequestMapping("logout")
    public Map logout(HttpServletRequest request) {
        loginService.logout(request);
        return MapTool.Mapok();

    }

}