package com.marko_eror.task_project.repository;

import java.util.Optional;

import com.marko_eror.task_project.model.Role;
import com.marko_eror.task_project.enums.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(RoleName roleName);
}