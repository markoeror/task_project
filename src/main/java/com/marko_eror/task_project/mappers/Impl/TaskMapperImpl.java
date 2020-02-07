package com.marko_eror.task_project.mappers.Impl;

import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.enums.TaskStatus;
import com.marko_eror.task_project.mappers.TaskMapper;
import com.marko_eror.task_project.model.Task;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class TaskMapperImpl implements TaskMapper {

    @Override
    public TaskDTO taskToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setId(task.getId());
        taskDTO.setDeadline(task.getDeadline());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setShortname(task.getShortname());
        taskDTO.setTaskStatus(task.getTaskStatus());
        taskDTO.setUserId(task.getUser().getId().intValue());
        if(task.getProject() !=null){
            taskDTO.setProjectName(task.getProject().getProjectName());
        }
        taskDTO.setUsersName(task.getUser().getUsername());
        return taskDTO;
    }

    @Override
    public Set<TaskDTO> taskSetToTaskDTOSet(Set<Task> tasks) {
        Set<TaskDTO> taskDTOS = new HashSet<>();
        tasks.forEach(task -> taskDTOS.add(taskToTaskDTO(task)));
        return taskDTOS;
    }

    @Override
    public Task taskDTOtoTask(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTaskStatus(TaskStatus.NEW);
        task.setDescription(taskDTO.getDescription());
        task.setDeadline(taskDTO.getDeadline());
        task.setShortname(taskDTO.getShortname());
        return task;
    }

    @Override
    public Task taskoDTOtoTaskUpdate(TaskDTO taskDTO) {
        Task task = new Task();
        task.setId(taskDTO.getId());
        task.setTaskStatus(taskDTO.getTaskStatus());
        return task;
    }
}
