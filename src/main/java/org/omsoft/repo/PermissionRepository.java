package org.omsoft.repo;

import org.omsoft.entity.Permission;
import org.omsoft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission,Integer> {
    @Query("Select p from Permission p where p.roleName IN (:rName)")
    public List<Permission> findByRoleName(List<String> rName);

    @Transactional
    @Modifying
    @Query("delete Permission p where p.roleId IN (:roleIds)")
    public void deleteByRoleIds(List<Integer> roleIds);
}
