package com.marko_eror.task_project.services;

import com.marko_eror.task_project.dto.TaskDTO;

import java.util.Set;

public interface TaskService {


    Set<TaskDTO> getTasksByUser(String username);

    Set<TaskDTO> updateTasksStatus(Set<TaskDTO> taskDTOSet);

    TaskDTO addUserToTask(Long userId, Long taskId);

    TaskDTO removeUserFromTask(Long userId, Long taskId);

    TaskDTO updateTask(TaskDTO taskDTO);
    Set<TaskDTO> updateTaskUser(TaskDTO taskDTO);

    Set<TaskDTO> getTasksByUserAdmin();

    Set<TaskDTO> updateTaskAdmin(TaskDTO taskDTO);
    Set<TaskDTO> createAndAddTaskToProject(TaskDTO taskDTO, Long projectId);

    Set<TaskDTO> deleteTaskById(Integer taskId);
}
