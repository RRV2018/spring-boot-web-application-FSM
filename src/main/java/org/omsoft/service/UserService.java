package org.omsoft.service;

import org.omsoft.entity.Permission;
import org.omsoft.entity.Role;
import org.omsoft.entity.User;
import org.omsoft.repo.PermissionRepository;
import org.omsoft.repo.RoleRepository;
import org.omsoft.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    UserRepository userRepo;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PermissionRepository permissionRepository;


    public Integer addUser(User user){
        User result = userRepo.save(user);
        return user.getUserId();
    }

    public Optional<User> findById(Integer userID){
        return userRepo.findById(userID);
    }

    public User findByName(String userName){
        return userRepo.findByUserName(userName);
    }

    public List<User> findAllUsers(){
        return userRepo.findAll();
    }

    public boolean deleteUser(Integer userId){
        if(deleteRole(userId)) {
            userRepo.deleteById(userId);
        }
        return true;
    }

    private boolean deleteRole(Integer userId){
        List<Role> result = roleRepository.findByUserId(userId);
        List<Integer> roleIDs = result.stream().map(r -> r.getRoleId()).collect(Collectors.toList());
        if(deletePermission(roleIDs)){
            roleRepository.deleteByUserId(userId);
        }
        return true;
    }


    private boolean deletePermission(List<Integer> roleIDs){
        permissionRepository.deleteByRoleIds(roleIDs);
        return true;
    }

    public Integer addRole(Role role){
        Role result = roleRepository.save(role);
        System.out.println(result);
        return role.getUserId();
    }

    public List<Role> fetchRoles(Integer userId){
        List<Role> result = roleRepository.findByUserId(userId);
        System.out.println(result);
        return result;
    }


    public List<String> getUserRolesUsingUserName(String userName){
        List<Role> roles =  roleRepository.findByUserName(userName);
        List<String> roleList = roles.stream().map(role -> role.getRoleName()).collect(Collectors.toList());
        return roleList;
    }

    public Integer addPermission(Permission permission){
        Permission result = permissionRepository.save(permission);
        System.out.println(result);
        return permission.getPermissionId();
    }


    public List<String> getRolePermissions(List<String> roles){
        List<Permission> permissions =  permissionRepository.findByRoleName(roles);
        List<String> permList = permissions.stream().map(role -> role.getPermissionName()).collect(Collectors.toList());
        return permList;
    }


}