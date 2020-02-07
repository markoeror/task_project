package com.marko_eror.task_project.mappers.Impl;

import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.dto.UserDetailsDTO;
import com.marko_eror.task_project.mappers.UserMapper;
import com.marko_eror.task_project.model.User;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public UserDTO userToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setName(user.getName());
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setSurname(user.getSurname());
        userDTO.setUsername(user.getUsername());
        return userDTO;
    }

    @Override
    public User userDetailsDtoToUser(UserDetailsDTO userDetailsDTO) {
        User user = new User();
        user.setUsername(userDetailsDTO.getUsername());
        return user;
    }

    @Override
    public User userDtoToUser(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setUsername(userDTO.getUsername());
        return user;
    }

    @Override
    public Set<User> usersDtoToUsers(Set<UserDTO> userDTOSet) {
        return userDTOSet.stream().map(userDto -> userDtoToUser(userDto)).collect(Collectors.toSet());
    }
}
