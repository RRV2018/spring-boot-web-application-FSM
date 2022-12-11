package org.omsoft.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.omsoft.dto.UserCredentials;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
    @GetMapping("/logout")
    public ModelAndView logout(){
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.removeAttribute("message");
        currentUser.logout();
        return new ModelAndView("logout");
    }

    @PostMapping("/login")
    public ModelAndView login(@RequestParam("userName") String userName, @RequestParam("password") String password){
        UsernamePasswordToken token = new UsernamePasswordToken(userName,password);
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        try {
            token.setRememberMe(false);
            currentUser.login(token);
            session.removeAttribute("message");
            session.setAttribute("user",currentUser.getPrincipal());
            System.out.println("Logged in successfully");
        } catch (UnknownAccountException uae) {
            System.out.println("There is no user with username of " + token.getPrincipal());
            session.setAttribute("message","Incorrect user credentials.");
            return new ModelAndView("login");
        } catch (IncorrectCredentialsException ice) {
            System.out.println("Password for account " + token.getPrincipal() + " was incorrect!");
            session.setAttribute("message","Incorrect user credentials.");
            return new ModelAndView("login");
        } catch (LockedAccountException lae) {
            System.out.println("The account for username " + token.getPrincipal() + " is locked.  " +
                    "Please contact your administrator to unlock it.");
            session.setAttribute("message","Your account is locked.");
            return new ModelAndView("login");
        } catch (AuthenticationException ae) {
            session.setAttribute("message","Unable to login try after some time");
            return new ModelAndView("login");
        }
        return new ModelAndView("welcome");
    }
}
