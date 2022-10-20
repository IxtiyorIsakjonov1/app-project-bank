package com.example.appprojectbank.Repository;


import com.example.appprojectbank.Entity.Role;
import com.example.appprojectbank.Entity.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role,Integer> {
    List<Role> findAllByName(RoleName name);
}
