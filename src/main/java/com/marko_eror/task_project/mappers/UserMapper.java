package com.marko_eror.task_project.mappers;

import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.dto.UserDetailsDTO;
import com.marko_eror.task_project.model.User;

import java.util.Set;

public interface UserMapper {

    UserDTO userToUserDTO(User user);

    User userDetailsDtoToUser(UserDetailsDTO userDetailsDTO);

    User userDtoToUser(UserDTO userDTO);

    Set<User> usersDtoToUsers(Set<UserDTO> userDTOSet);
}
