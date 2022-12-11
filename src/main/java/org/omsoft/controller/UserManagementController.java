package org.omsoft.controller;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.omsoft.constant.AppConstant;
import org.omsoft.dto.UserCredentials;
import org.omsoft.entity.Permission;
import org.omsoft.entity.Role;
import org.omsoft.entity.User;
import org.omsoft.service.UserService;
import org.omsoft.util.LoginUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UserManagementController {
    @Autowired
    UserService userService;

    @RequiresRoles({"admin"})
    @PostMapping("/addUser")
    public ModelAndView addUser(@ModelAttribute("userForm") UserCredentials userData){
        ModelAndView model = new ModelAndView("welcome");
        try {
            System.out.println(userData);
            Integer userID = saveUser(userData);
            Integer roleID = saveRole(userData,userID);
            savePermission(userData,roleID);
            model.addObject("message", "User added successfully");
        }catch (NoSuchAlgorithmException algo){
            model.addObject("error_message", "Error while adding user, Error ="+algo.getMessage());
        }catch (Exception ex){
            model.addObject("error_message", "Error while adding user, Error ="+ex.getMessage());
        }
        return model;
    }
    private Integer saveUser(UserCredentials userData) throws NoSuchAlgorithmException{
        User user1 = new User();
        String salt = LoginUtil.getSalt();
        user1.setUserName(userData.getUserName());
        user1.setUserPassword(userData.getPassword());
        user1.setPasswordHash(LoginUtil.getSecurePassword(userData.getPassword(),salt));
        user1.setSalt(salt);
        user1.setStatus(AppConstant.ACTIVE.name());
        user1.setCreatedOn(LocalDateTime.now());
        user1.setModifiedOn(LocalDateTime.now());
        return userService.addUser(user1);
    }

    private Integer saveRole(UserCredentials userData, Integer userId){
        Role role = new Role();
        role.setUserName(userData.getUserName());
        role.setRoleName(userData.getUserRole());
        role.setUserId(userId);
        return userService.addRole(role);
    }

    private void savePermission(UserCredentials userData, Integer roleId){
        userData.getPermission().stream().forEach(perm -> {
            Permission permission = new Permission();
            permission.setRoleName(userData.getUserRole());
            permission.setPermissionName(perm);
            permission.setRoleId(roleId);
            userService.addPermission(permission);
        });
    }

    @RequiresPermissions({"read"})
    @PostMapping("searchUser")
    public ModelAndView searchUser(@RequestParam("userName") String userName){
        System.out.println("In searchUser Post");
        ModelAndView model = new ModelAndView("welcome");
        List<User> userList = null;
        if(StringUtils.isNoneBlank(userName)){
            User user = userService.findByName(userName);
            if(user != null){
                userList = new ArrayList<>();
                userList.add(user);
            }
        }else{
            userList = userService.findAllUsers();
        }
        model.addObject("action","Search");
       if(userList != null && userList.size() > 0) {
            model.addObject("userList", userList);
        }else{
            model.addObject("error_message", "No user found with user name "+userName);
        }
        return model;
    }

    @RequiresRoles({"admin"})
    @PostMapping("/deleteUser")
    public ModelAndView addUser(@RequestParam("userId") Integer userId){
        ModelAndView model = new ModelAndView("welcome");
        System.out.println("Deleting user "+userId);
        if (userService.deleteUser(userId)){
            model.addObject("message", "User data deleted successfully.");
        }else {
            model.addObject("error_message", "Can not delete user, contact to support team.");
        }

        return model;
    }
    @ResponseBody
    @PostMapping("/loadRoles")
    public List<Role> showRoles(@RequestParam("userId") Integer userId){
        System.out.println("Fetch Roles "+userId);
        List<Role> roles = userService.fetchRoles(userId);
        return roles;
    }

}
