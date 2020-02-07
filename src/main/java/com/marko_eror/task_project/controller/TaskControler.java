package com.marko_eror.task_project.controller;

import com.marko_eror.task_project.dto.ProjectDTO;
import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.dto.UserDTO;
import com.marko_eror.task_project.model.Project;
import com.marko_eror.task_project.services.ProjectService;
import com.marko_eror.task_project.services.TaskService;
import javafx.concurrent.Task;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class TaskControler {
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    private final TaskService taskService;
    private final ProjectService projectService;
    @Autowired
    public TaskControler(TaskService taskService, ProjectService projectService) {
        this.taskService = taskService;
        this.projectService = projectService;
    }

    //Method for returning Task's by for User
    @PostMapping("/tasksUser")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Set<TaskDTO>> userTasksByUsernameUSER(@RequestBody UserDTO userDTO) {
        logger.info("Received get tasks collection by username request");
        try {
            Set<TaskDTO> taskDTOS = taskService.getTasksByUser(userDTO.getUsername());
            logger.debug("Get Tasks collection for User returned.");
            return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Get Tasks collection for User returned not found exception");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    //Method for returning Task's by for User
    @PostMapping("/tasksAdmin")
    @PreAuthorize(" hasRole('ADMIN')")
    public ResponseEntity<Set<TaskDTO>> userTasksByUsernameADMIN(@RequestBody UserDTO userDTO) {
        logger.info("Received get tasks collection by username request");
        try {
            Set<TaskDTO> taskDTOS = taskService.getTasksByUserAdmin();
            logger.debug("Get Tasks collection for User returned.");
            return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Get Tasks collection for User returned not found exception");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    //Updating Task's status
    @PostMapping("/updateTasksStatus")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<Set<TaskDTO>> updateTasksStatus(@RequestBody Set<TaskDTO> taskDTOSet) {
        logger.info("Received get tasks collection by username request");
        try {
            Set<TaskDTO> taskDTOS = taskService.updateTasksStatus(taskDTOSet);
            logger.debug("Get Tasks collection for User returned.");
            return new ResponseEntity<>(taskDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Get Tasks collection for User returned not found exception");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Creating and adding Task to Project
    @PostMapping("/createTaskAndAddToProject/{projectId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<TaskDTO>> createTaskAndAddToProject(@RequestBody TaskDTO taskDTO,@PathVariable Long projectId) {
        logger.info("Received create task and add to project request");
        try {
            Set<TaskDTO> tasksDTOS = taskService.createAndAddTaskToProject(taskDTO,projectId);
            logger.debug("Task created and added to project.");
            return new ResponseEntity<>(tasksDTOS, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while creating and adding task to project");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Adding User from Task
    @GetMapping("/addUserToTask/{userId}/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> addUserToTask(@PathVariable Long userId,@PathVariable Long taskId) {
        logger.info("Received add user to task");
        try {
            TaskDTO taskDTO = taskService.addUserToTask(userId,taskId);
            logger.debug("User added to task.");
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while adding user to task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Removing User from Task
    @GetMapping("/removeUserFromTask/{userId}/{taskId}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<TaskDTO> removeUserFromTask(@PathVariable Long userId,@PathVariable Long taskId) {
        logger.info("Received remove user from task");
        try {
            TaskDTO taskDTO = taskService.removeUserFromTask(userId,taskId);
            logger.debug("User added to task.");
            return new ResponseEntity<>(taskDTO, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while removing user from task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Removing User from Task
    @PostMapping("/updateTaskAdmin")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Set<TaskDTO>> updateTaskAdmin(@RequestBody TaskDTO taskDTO) {
        logger.info("Received remove user from task");
        try {
            Set<TaskDTO> SetTaskDtoModified = taskService.updateTaskAdmin(taskDTO);
            logger.debug("User added to task.");
            return new ResponseEntity<>(SetTaskDtoModified, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while removing user from task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @PostMapping("/updateTaskUser")
    @PreAuthorize("hasRole('USER') ")
    public ResponseEntity<Set<TaskDTO>> updateTask(@RequestBody TaskDTO taskDTO) {
        logger.info("Received remove user from task");
        try {
            Set<TaskDTO> SetTaskDtoModified = taskService.updateTaskUser(taskDTO);
            logger.debug("User added to task.");
            return new ResponseEntity<>(SetTaskDtoModified, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while removing user from task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/deleteTask/{taskId}")
    @PreAuthorize("hasRole('ADMIN') ")
    public ResponseEntity<Set<TaskDTO>> deleteTask(@PathVariable Integer taskId) {
        logger.info("Received remove user from task");
        try {
            Set<TaskDTO> taskList = taskService.deleteTaskById(taskId);
            logger.debug("User added to task.");
            return new ResponseEntity<>(taskList, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error while removing user from task");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
