package com.marko_eror.task_project.dto;

import java.util.Set;

public class ProjectDTO {
    private Long Id;
    private String projectName;
    private Set<TaskDTO> tasks;
    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<TaskDTO> getTaskDTOSet() {
        return tasks;
    }

    public void setTaskDTOSet(Set<TaskDTO> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "ProjectDTO{" +
                "Id=" + Id +
                ", projectName='" + projectName + '\'' +
                ", taskDTOSet=" + tasks +
                '}';
    }
}
