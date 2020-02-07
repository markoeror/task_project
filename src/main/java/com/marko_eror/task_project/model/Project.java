package com.marko_eror.task_project.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String projectName;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "projects")
    private Set<User> users = new HashSet<>();

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "project", orphanRemoval = true, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Task> tasks = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Task> getTasks() {
        return tasks;
    }

    public void setTasks(Set<Task> tasks) {
        this.tasks = tasks;
    }

    //Methods for synchronisation adding and removing tasks from project
    public void addTask(Task task) {
        tasks.add(task);
        task.setProject(this);
    }

    public void removeTask(Task task) {
        tasks.remove(task);
        task.setProject(null);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Project project = (Project) o;
        return Objects.equals(id, project.id);
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
