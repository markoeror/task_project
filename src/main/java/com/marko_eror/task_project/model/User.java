package com.marko_eror.task_project.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.NaturalId;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        }),
        @UniqueConstraint(columnNames = {
                "email"
        })
})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String name;
    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String surname;

    @NotBlank
    @Size(min = 3, max = 50)
    @Column(nullable = false)
    private String username;

    @NaturalId
    @NotBlank
    @Size(max = 50)
    @Email
    @Column(nullable = false)
    private String email;

    @NotBlank
    @Size(min = 6, max = 100)
    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "roleId"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinTable(name = "users_projects",
            joinColumns = @JoinColumn(name = "userId"),
            inverseJoinColumns = @JoinColumn(name = "projectId"))
    private Set<Project> projects = new HashSet<>();


    @OneToMany(fetch = FetchType.LAZY,mappedBy = "user", orphanRemoval = true, cascade = {CascadeType.DETACH, CascadeType.REFRESH, CascadeType.REMOVE, CascadeType.PERSIST})
    private Set<Task> tasks = new HashSet<>();

    public User() {
    }

    public User(String name, String surname, String username, String email, String password) {
        this.name = name;
        this.username = username;
        this.email = email;
        this.password = password;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    //Synchronise methods User and Project
    public void addProject(Project project) {
        projects.add(project);
        project.getUsers().add(this);
    }

    public void removeProject(Project project) {
        projects.remove(project);
        project.getUsers().remove(this);
    }

//    //Methods for synchronisation adding and removing tasks from user
//    public void addTask(Task task) {
//        taskSet.add(task);
//        task.setUser(this);
//    }
//
//    public void removeTask(Task task) {
//        taskSet.remove(task);
//        task.setUser(null);
//    }
}