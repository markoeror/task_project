package com.marko_eror.task_project.mappers.Impl;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.mappers.ProjectMapper;
import com.marko_eror.task_project.mappers.TaskMapper;
import com.marko_eror.task_project.model.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class ProjectMapperImpl implements ProjectMapper {
    private final TaskMapper taskMapper;

    @Autowired
    public ProjectMapperImpl(TaskMapper taskMapper) {
        this.taskMapper = taskMapper;
    }


    @Override
    public ProjectDTO projectToProjectDto(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectName(project.getProjectName());
        return projectDTO;
    }

    @Override
    public ProjectDTO projectToProjectDtoTasks(Project project) {
        ProjectDTO projectDTO = new ProjectDTO();
        projectDTO.setId(project.getId());
        projectDTO.setProjectName(project.getProjectName());
        projectDTO.setTaskDTOSet(taskMapper.taskSetToTaskDTOSet(project.getTasks()));
        return projectDTO;

    }

    @Override
    public Set<ProjectDTO> setProjectsToSetProjectsDTO(Set<Project> projectSet) {
        Set<ProjectDTO> projectDTOS = new HashSet<>();
        projectDTOS = projectSet.stream().map(project ->
                projectToProjectDto(project)).collect(Collectors.toSet());
        return projectDTOS;
    }

    @Override
    public Set<ProjectDTO> setProjectsToSetProjectsDTOTasksDTO(Set<Project> projectSet) {
        Set<ProjectDTO> projectDTOS = new HashSet<>();
        projectDTOS = projectSet.stream().map(project ->
                projectToProjectDtoTasks(project)).collect(Collectors.toSet());
        return projectDTOS;
    }

    @Override
    public Project projectDtoToProject(ProjectDTO projectDTO) {
        Project project = new Project();
        project.setProjectName(projectDTO.getProjectName());
        return project;
    }


}
