package com.marko_eror.task_project.services.Impl;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.mappers.ProjectMapper;
import com.marko_eror.task_project.mappers.TaskMapper;
import com.marko_eror.task_project.mappers.UserMapper;
import com.marko_eror.task_project.model.Project;
import com.marko_eror.task_project.model.Task;
import com.marko_eror.task_project.model.User;
import com.marko_eror.task_project.repository.ProjectRepository;
import com.marko_eror.task_project.repository.TaskRepository;
import com.marko_eror.task_project.repository.UserRepository;
import com.marko_eror.task_project.services.ProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.Set;

@Service
public class ProjectServiceImpl implements ProjectService {
    private final ProjectMapper projectMapper;
    private final UserMapper userMapper;
    private final TaskRepository taskRepository;
    private final UserRepository userRepository;
    private final ProjectRepository projectRepository;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskMapper taskMapper;

    @Autowired
    public ProjectServiceImpl(ProjectMapper projectMapper, UserMapper userMapper, TaskRepository taskRepository, UserRepository userRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.projectMapper = projectMapper;
        this.userMapper = userMapper;
        this.taskRepository = taskRepository;

        this.userRepository = userRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    @Override
    @Transactional
    public ProjectDTO findProjectById(Long id) {
        Project project = projectRepository.findProjectById(id);
        if (project == null) throw new EntityNotFoundException("There is no project with this id.");
        return projectMapper.projectToProjectDto(project);
    }

    @Override
    @Transactional
    public Set<ProjectDTO> getProjectDtoSetByUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new EntityNotFoundException("There is no user with this username.");
        Set<Project> setProjects = projectRepository.findProjectsByUsers(user);
//        Set<Project> setProjects = projectRepository.findProjectsByUserId(user.getId());
        if (setProjects == null) throw new EntityNotFoundException("There are no projects for this user.");
        Set<ProjectDTO> setProjectsDtos = projectMapper.setProjectsToSetProjectsDTOTasksDTO(setProjects);
        return setProjectsDtos;
    }
    @Override
    @Transactional
    public Set<ProjectDTO> getProjectDtoSet() {
        Set<Project> setProjects = new HashSet<>(projectRepository.findAll());
//        Set<Project> setProjects = projectRepository.findProjectsByUserId(user.getId());
        if (setProjects == null) throw new EntityNotFoundException("There are no projects for this user.");
        Set<ProjectDTO> setProjectsDtos = projectMapper.setProjectsToSetProjectsDTOTasksDTO(setProjects);
        return setProjectsDtos;
    }

    @Override
    @Transactional
    public Set<ProjectDTO> createProject(ProjectDTO projectDTO) {
        try {
            Project project = projectRepository.save(projectMapper.projectDtoToProject((projectDTO)));
            System.out.println("project"+project.toString());
            Set<ProjectDTO> projectsDTO = getProjectDtoSet();
            logger.debug("Created Project: " + project.toString());
            return projectsDTO;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    @Transactional
    public ProjectDTO assUsersToProject(Set<UserDTO> userDTOSet) {

        return null;
    }




}
