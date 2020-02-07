package com.marko_eror.task_project.services;

import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.model.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {

    UserDTO getUserByUsername(String auth);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Set<UserDTO> getAllUsersWithRoleUSER();

}
