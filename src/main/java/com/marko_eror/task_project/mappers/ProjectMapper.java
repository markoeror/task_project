package com.marko_eror.task_project.mappers;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.model.Project;

import java.util.Set;

public interface ProjectMapper {

    public ProjectDTO projectToProjectDto(Project project);
    public ProjectDTO projectToProjectDtoTasks(Project project);
    public Set<ProjectDTO> setProjectsToSetProjectsDTO(Set<Project> projectSet);

    public Set<ProjectDTO> setProjectsToSetProjectsDTOTasksDTO(Set<Project> projectSet);
    public Project projectDtoToProject(ProjectDTO projectDTO);

}
