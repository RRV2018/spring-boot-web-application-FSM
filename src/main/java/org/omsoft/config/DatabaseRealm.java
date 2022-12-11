package org.omsoft.config;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.omsoft.constant.AppConstant;
import org.omsoft.entity.User;
import org.omsoft.service.UserService;
import org.omsoft.util.LoginUtil;

import java.util.List;

public class DatabaseRealm extends AuthorizingRealm {

    private UserService userService;

    public DatabaseRealm(){

    }
    public DatabaseRealm(UserService service){
        userService = service;
    }
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException{
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String userName = usernamePasswordToken.getUsername();
        char[] password = usernamePasswordToken.getPassword();
        try {
            User user = userService.findByName(userName); // get from DB;
            if (user != null){
                String securePassword = LoginUtil.getSecurePassword(new String(password),user.getSalt());
                if(StringUtils.isNoneEmpty(securePassword) && securePassword.equals(user.getPasswordHash()) && AppConstant.ACTIVE.name().equals(user.getStatus())){
                   return new SimpleAuthenticationInfo(user,password,this.getName());
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }

        return null;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal){
        System.out.println(principal.getRealmNames());
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        List<String> userRoles = userService.getUserRolesUsingUserName(((User)principal.getPrimaryPrincipal()).getUserName()); // get user rolse from DB
        List<String> permissions = userService.getRolePermissions(userRoles);
        authorizationInfo.addRoles(userRoles);
        authorizationInfo.addStringPermissions(permissions);
        return authorizationInfo;
    }

}
