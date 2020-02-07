package com.marko_eror.task_project.repository;

import com.marko_eror.task_project.model.Project;
import com.marko_eror.task_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ProjectRepository extends JpaRepository<Project, String> {

    Project findProjectById(Long id);

    Set<Project> findProjectsByUsers(User user);

    @Query("SELECT u FROM User u JOIN FETCH u.projects WHERE u.id = (:id)")
    Set<Project> findProjectsByUserId(@Param("id") Long id);


}
