package com.marko_eror.task_project.repository;


import com.marko_eror.task_project.model.Role;
import com.marko_eror.task_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findUserByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    User findUserById(Long userId);

    @Query(value = "SELECT * FROM users JOIN users_roles ON users.id = users_roles.user_id JOIN roles ON roles.id = users_roles.role_id WHERE roles.name= ?", nativeQuery = true)
    List<User> findUserByRoles(String role);
}