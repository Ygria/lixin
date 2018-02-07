package com.xiyoukeji.lixin.service;

import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

/**
 * Created by ygria on 2018/2/6.
 */
@Service
public class LoginService {
    @Transactional
    public void login(Long id,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("cur_user_id", id);

    }

    @Transactional
    public Long getCurrentUserId(HttpServletRequest request){
        HttpSession session = request.getSession();
        return (Long) session.getAttribute("cur_user_id");
    }

    @Transactional
    public void logout(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("cur_user_id");

    }

}
