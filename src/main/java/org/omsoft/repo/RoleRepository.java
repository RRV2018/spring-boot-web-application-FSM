package org.omsoft.repo;

import org.omsoft.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoleRepository extends JpaRepository<Role,Integer>, CrudRepository<Role,Integer> {
    public List<Role> findByUserName(String uName);

    public List<Role> findByUserId(Integer userId);

    public void deleteByUserId(Integer userId);



}
