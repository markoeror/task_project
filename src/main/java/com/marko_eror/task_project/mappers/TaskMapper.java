package com.marko_eror.task_project.mappers;

import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.model.Task;

import java.util.HashSet;
import java.util.Set;

public interface TaskMapper {
        TaskDTO taskToTaskDTO(Task task);
        Set<TaskDTO> taskSetToTaskDTOSet(Set<Task> tasks);
        Task taskDTOtoTask(TaskDTO taskDTO);
        Task taskoDTOtoTaskUpdate(TaskDTO taskDTO);

}
