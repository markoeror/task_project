package com.marko_eror.task_project.model;

import javax.persistence.*;

import com.marko_eror.task_project.enums.RoleName;
import org.hibernate.annotations.NaturalId;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(length = 60)
    private RoleName name;

    @ManyToMany(fetch = FetchType.LAZY,mappedBy = "roles")
    private Set<User> users = new HashSet<>();
    public Role() {}

    public Role(RoleName name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RoleName getName() {
        return name;
    }

    public void setName(RoleName name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}