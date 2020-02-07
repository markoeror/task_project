package com.marko_eror.task_project.services;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.model.User;

import java.util.Set;

public interface ProjectService {

    public ProjectDTO findProjectById(Long id);
    public Set<ProjectDTO> getProjectDtoSetByUser (String user);

    Set<ProjectDTO> createProject(ProjectDTO projectDTO);

    ProjectDTO assUsersToProject(Set<UserDTO> userDTOSet);


    Set<ProjectDTO> getProjectDtoSet();
}
