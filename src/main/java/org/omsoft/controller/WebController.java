package org.omsoft.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.omsoft.repo.RoleMasterRepository;
import org.omsoft.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class WebController {

    @Autowired
    RoleMasterRepository roles;

    @GetMapping({"/","/login"})
    public ModelAndView welcome(){
        if(getLoggedInUser() != null){
            return new ModelAndView("welcome");
        }
        LoginUtil.logout();
        return new ModelAndView("login");
    }

    @GetMapping({"/addUser","/searchUser"})
    public ModelAndView redirectToLogin(@RequestParam(name="action",required=false) String action){
        ModelAndView model = new ModelAndView("login");
        System.out.println(action);
        if(StringUtils.isNoneEmpty(action) || getLoggedInUser() != null){
            model = new ModelAndView("welcome");
        }
        if(StringUtils.equals("Add",action)){
            model.addObject("roles",roles.findAll());
        }
        model.addObject("action",action);
        return model;

    }

    private String getLoggedInUser(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        return (String) session.getAttribute("useName");
    }
}
