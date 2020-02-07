package com.marko_eror.task_project.dto;

import com.marko_eror.task_project.model.Task;

import java.util.HashSet;
import java.util.Set;

public class UserDTO {

    private Long id;
    private String name;
    private String surname;
    private String username;
    private String email;
    private String password;
    private Set<ProjectDTO> projectDTOS= new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<ProjectDTO> getProjectDTOS() {
        return projectDTOS;
    }

    public void setProjectDTOS(Set<ProjectDTO> projectDTOS) {
        this.projectDTOS = projectDTOS;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
