package com.marko_eror.task_project.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.marko_eror.task_project.enums.TaskStatus;
import com.marko_eror.task_project.model.Project;

import java.time.LocalDate;

public class TaskDTO {
    private Long id;
    private TaskStatus taskStatus;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate deadline;
    private String description;
    private String shortname;
    private String projectName;
    private String usersName;
    private int userId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public void setDeadline(LocalDate deadline) {
        this.deadline = deadline;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortname() {
        return shortname;
    }

    public void setShortname(String shortname) {
        this.shortname = shortname;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getUsersName() {
        return usersName;
    }

    public void setUsersName(String usersName) {
        this.usersName = usersName;
    }

    @Override
    public String toString() {
        return "TaskDTO{" +
                "id=" + id +
                ", taskStatus=" + taskStatus +
                ", deadline=" + deadline +
                ", description='" + description + '\'' +
                ", shortname='" + shortname + '\'' +
                ", projectName='" + projectName + '\'' +
                ", usersName='" + usersName + '\'' +
                '}';
    }
}
