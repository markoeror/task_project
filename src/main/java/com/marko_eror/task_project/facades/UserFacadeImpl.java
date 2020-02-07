package com.marko_eror.task_project.facades;

import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.mappers.UserMapper;
import com.marko_eror.task_project.model.User;
import com.marko_eror.task_project.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserFacadeImpl {

    private final UserService userService;
    private final UserMapper userMapper;

    @Autowired
    public UserFacadeImpl(UserService userService, UserMapper userMapper) {
        this.userService = userService;
        this.userMapper = userMapper;
    }




}


