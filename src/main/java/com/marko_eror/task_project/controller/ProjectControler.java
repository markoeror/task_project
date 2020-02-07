package com.marko_eror.task_project.controller;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.model.Project;
import com.marko_eror.task_project.services.ProjectService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class ProjectControler {

    private final ProjectService projectService;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public ProjectControler(ProjectService projectService) {
        this.projectService = projectService;
    }

    // Method for returning Projects by username
    @GetMapping("/projects/{username}")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Set<ProjectDTO>> userProjectsByUsername1(@PathVariable String username) {
        logger.info("Received get projects collection by username request");
        try {
            Set<ProjectDTO> projectsDTO = projectService.getProjectDtoSetByUser(username);
            logger.debug("Get Projects collection for User returned.");
            return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Get Projects collection for User returned not found exception");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    // Method for returning Projects by User
    @GetMapping("/projects")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<ProjectDTO>> userProjectsbyAdmin() {
        logger.info("Received get projects collection by username request");
        try {
            Set<ProjectDTO> projectsDTO = projectService.getProjectDtoSet();
            logger.debug("Get Projects collection for User returned.");
            return new ResponseEntity<>(projectsDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Get Projects collection for User returned not found exception");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    // Method for creating
    @PostMapping("/admin/project")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<ProjectDTO>> createProject(@RequestBody ProjectDTO projectDTO) {
        try {
            Set<ProjectDTO> projects =  projectService.createProject(projectDTO);
            logger.debug("Project created.");
            return new ResponseEntity<>(projects, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while creating Project");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/admin/addUsersToProject")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ProjectDTO> AddUsersToProject(@RequestBody Set<UserDTO> userDTOSet) {
        try {
            ProjectDTO  projectDTO = projectService.assUsersToProject(userDTOSet);
            logger.debug("Users added to Project.");
            return new ResponseEntity<>(projectDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while adding Users to Project");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
