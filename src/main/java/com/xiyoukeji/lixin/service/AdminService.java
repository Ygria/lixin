package com.xiyoukeji.lixin.service;

import com.xiyoukeji.lixin.domain.Admin;
import com.xiyoukeji.lixin.repository.BaseDao;
import com.xiyoukeji.lixin.type.AdminRoleEnum;
import com.xiyoukeji.lixin.util.ErrCodeException;
import com.xiyoukeji.lixin.util.MapTool;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ygria on 2018/2/5.
 */

@Service
public class AdminService {
    @Resource
    BaseDao baseDao;

    @Resource
    LoginService loginService;

    @Transactional
    public Map userLogin(String username, String password, HttpServletRequest request, HttpServletResponse response) {

        String hql = "from Admin admin where admin.username=:username";
        Admin admin = (Admin) baseDao.get(hql, MapTool.Map().put("username", username));
        if (admin.getDisabled()) {
            throw new ErrCodeException(10001, "管理员已被禁用，请联系超级管理员解禁");
        }
        if (!new BCryptPasswordEncoder().matches(password, admin.getPassword())) {
            throw new ErrCodeException(10002, "管理员密码错误!");
        }
        loginService.login(admin.getId(),request);
        return MapTool.Mapok();
    }

    @Transactional
    public Map saveOrUpdateAdmin(Long id, String username, String password) {
        Admin admin;

        if (id != null) {
            admin = getAdmin(id);
            if(username!=admin.getUsername()&&getAdminByUsername(username)!=null&&!admin.equals(getAdminByUsername(username))){
                throw new ErrCodeException(10004,"该用户名已经存在！");
            }
        } else {
            if(getAdminByUsername(username)!=null){
                throw new ErrCodeException(10004,"该用户名已经存在！");
            }
            admin = new Admin();
            admin.setAdmin_role(AdminRoleEnum.NORMAL);

        }
        admin.setUsername(username);
        admin.setPassword(new BCryptPasswordEncoder().encode(password));
        baseDao.saveOrUpdate(admin);
        return MapTool.Mapok();
    }

    @Transactional
    public Admin getAdmin(Long id) {
        Admin admin = (Admin) baseDao.get(Admin.class, id);
        return admin;

    }

    @Transactional
    public Map adminList(Integer pageNum, Integer rowNum, String username, Boolean disabled) {
        pageNum = pageNum == null ? 1 : pageNum;
        rowNum = rowNum == null ? 10 : rowNum;
        String hql = "from Admin admin where 1=1 ";
        Map param = new HashMap();
        if (username != null) {
            hql += " and admin.username like:username";
            param.put("username", "%" + username + "%");
        }
        if (disabled != null) {
            hql += " and admin.disabled=:disabled";
            param.put("disabled", disabled);
        }
        List<Admin> admins = baseDao.find(hql, (pageNum - 1) * rowNum, rowNum, param);
        MapTool data = MapTool.Map().put("data", admins).put("pageNum", pageNum).put("rowNum", rowNum).put("totalPageNum", ((Double) Math.ceil((double) baseDao.count(hql, param) / rowNum)).intValue());

        return MapTool.Mapok().put("data",data);


    }

    @Transactional
    public Map disabledAdmin(Long id) {
        Admin admin = getAdmin(id);
        Boolean disabled = !admin.getDisabled();
        admin.setDisabled(disabled);
        baseDao.update(admin);
        return MapTool.Mapok();

    }

    @Transactional
    public Admin getCurAdmin(HttpServletRequest request){
        Long id = loginService.getCurrentUserId(request);
        if(id==null){
            throw new ErrCodeException(10003,"用户未登录！");
        }
        Admin admin = getAdmin(id);
        return admin;
    }

    /*@Transactional
    public Map change*/


    @Transactional
    public Admin getAdminByUsername(String username){
        String hql = "from Admin admin where admin.username=:username";
        return (Admin) baseDao.get(hql,MapTool.Map().put("username",username));

    }



}
