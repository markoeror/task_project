package com.marko_eror.task_project.services.Impl;

import com.marko_eror.task_project.dto.TaskDTO;
import com.marko_eror.task_project.mappers.TaskMapper;
import com.marko_eror.task_project.model.Project;
import com.marko_eror.task_project.model.Task;
import com.marko_eror.task_project.model.User;
import com.marko_eror.task_project.repository.ProjectRepository;
import com.marko_eror.task_project.repository.TaskRepository;
import com.marko_eror.task_project.repository.UserRepository;
import com.marko_eror.task_project.services.TaskService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Service
public class TaksServiceImpl implements TaskService {
    private final UserRepository userRepository;
    private final TaskRepository taskRepository;
    private final ProjectRepository projectRepository;
    private final TaskMapper taskMapper;
    private final org.slf4j.Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public TaksServiceImpl(UserRepository userRepository, TaskRepository taskRepository, ProjectRepository projectRepository, TaskMapper taskMapper) {
        this.userRepository = userRepository;
        this.taskRepository = taskRepository;
        this.projectRepository = projectRepository;
        this.taskMapper = taskMapper;
    }

    // Method for returning tasks by ADMIN
    @Override
    @Transactional
    public Set<TaskDTO> getTasksByUserAdmin() {
        List<Task> taskList=taskRepository.findAll();
        Set<Task> setTasks = new HashSet<>(taskList);
//        Set<Task> setTasks1 = taskRepository.findTasksByUserId(user.getId().intValue());
        if (setTasks == null) throw new EntityNotFoundException("There are no tasks for this user.");
        return taskMapper.taskSetToTaskDTOSet(setTasks);
    }


    // Method for returning tasks by USER
    @Override
    @Transactional
    public Set<TaskDTO> getTasksByUser(String username) {
        User user = userRepository.findUserByUsername(username);
        if (user == null) throw new EntityNotFoundException("There is no user with this username.");
        Set<Task> setTasks = taskRepository.findTaskByUser(user);
        Set<Task> setTasks1 = taskRepository.findTasksByUserId(user.getId().intValue());
        if (setTasks == null) throw new EntityNotFoundException("There are no tasks for this user.");
        return taskMapper.taskSetToTaskDTOSet(setTasks1);
    }

    // Method for updating task status
    @Override
    @Transactional
    public Set<TaskDTO> updateTasksStatus(Set<TaskDTO> taskDTOSet) {
        Set<TaskDTO> tasksDtosForReturning = new HashSet<>();
        Set<Task> tasks = new HashSet<>();;
        taskDTOSet.forEach(taskDTO ->{
            Task task = taskRepository.getTasksById(taskDTO.getId());
            if (task == null) throw new EntityNotFoundException("There is no task with this id.");
            task.setTaskStatus(taskDTO.getTaskStatus());
            tasks.add(task);
            TaskDTO taskDTO1 = taskMapper.taskToTaskDTO(task);
            tasksDtosForReturning.add(taskDTO1);
        });
        try{
            taskRepository.saveAll(tasks);
            logger.info("Updated user tasks status");
            return tasksDtosForReturning;
        }catch (Exception e) {
            logger.error("Error updating users tasks status");
            throw e;
        }
    }
    // Method adding user to task
    @Override
    @Transactional
    public TaskDTO addUserToTask(Long userId, Long taskId) {
        Task task = taskRepository.getTasksById(taskId);
        if (task == null) throw new EntityNotFoundException("There is no task with this id.");
        User user = userRepository.findUserById(userId);
        if (user == null) throw new EntityNotFoundException("There is no user with this id.");
        task.setUser(user);
        try{
            taskRepository.save(task);
            logger.info("Saved task with added user");
            return taskMapper.taskToTaskDTO(task);
        }catch (Exception e) {
            logger.error("Error saving task.");
            throw e;
        }
    }
    // Method method for removing user from task
    @Override
    @Transactional
    public TaskDTO removeUserFromTask(Long userId, Long taskId) {
        Task task = taskRepository.getTasksById(taskId);
        if (task == null) throw new EntityNotFoundException("There is no task with this id.");
        User user = userRepository.findUserById(userId);
        if (user == null) throw new EntityNotFoundException("There is no user with this id.");
        User user1 = new User();
        user1.setId((long) 0);
        task.setUser(user1);

        try{
            taskRepository.save(task);
            logger.info("User removed from task");
            return taskMapper.taskToTaskDTO(task);
        }catch (Exception e) {
            logger.error("Error removing user from task.");
            throw e;
        }
    }
    // Method for updating task
    @Override
    @Transactional
    public TaskDTO updateTask(TaskDTO taskDTO) {
        System.out.println(taskDTO.getId());
        Task taskFromDB = taskRepository.getTasksById(taskDTO.getId());
        if (taskFromDB == null) throw new EntityNotFoundException("There is no task with this id.");
        Task task = taskMapper.taskDTOtoTask(taskDTO);
        User user = userRepository.findUserByUsername(taskDTO.getUsersName());
        if (user == null) throw new EntityNotFoundException("There is no user with this username.");
        task.setUser(user);
        task.setProject(taskFromDB.getProject());
        Task task1 = taskRepository.save(task);
        return taskMapper.taskToTaskDTO(task1);
    }
    // Method for returning tasks by USER
    @Override
    @Transactional
    public Set<TaskDTO> updateTaskUser(TaskDTO taskDTO) {
        System.out.println(taskDTO.getId());
        Task taskFromDB = taskRepository.getTasksById(taskDTO.getId());
        if (taskFromDB == null) throw new EntityNotFoundException("There is no task with this id.");
        Task task = taskMapper.taskDTOtoTask(taskDTO);
        User user = userRepository.findUserByUsername(taskDTO.getUsersName());
        if (user == null) throw new EntityNotFoundException("There is no user with this username.");
        task.setUser(user);
        task.setProject(taskFromDB.getProject());
        task.setTaskStatus(taskDTO.getTaskStatus());
        try{
            Task task1 = taskRepository.save(task);
            Set<Task> updatedTaskSet = taskRepository.findTasksByUserId(user.getId());
            logger.info("Task updated by user");
            return taskMapper.taskSetToTaskDTOSet(updatedTaskSet);
        }catch (Exception e) {
            logger.error("Error updating task bu user.");
            throw e;
        }
    }
    // Method for returning tasks by ADMIN
    @Override
    @Transactional
    public Set<TaskDTO> updateTaskAdmin(TaskDTO taskDTO) {
        System.out.println(taskDTO.getId());
        Task taskFromDB = taskRepository.getTasksById(taskDTO.getId());
        if (taskFromDB == null) throw new EntityNotFoundException("There is no task with this id.");
        Task task = taskMapper.taskDTOtoTask(taskDTO);
        User user = userRepository.findUserByUsername(taskDTO.getUsersName());
        if (user == null) throw new EntityNotFoundException("There is no user with this username.");
        task.setUser(user);
        task.setProject(taskFromDB.getProject());
        task.setTaskStatus(taskDTO.getTaskStatus());
        try{
            Task task1 = taskRepository.save(task);
            List<Task> taskList= taskRepository.findAll();
            Set<Task> updatedTaskSet = new HashSet<>(taskList);
            logger.info("Task updated by user");
            return taskMapper.taskSetToTaskDTOSet(updatedTaskSet);
        }catch (Exception e) {
            logger.error("Error updating task by admin.");
            throw e;
        }
    }
    // Method for creating task and adding it to project
    @Override
    @Transactional
    public Set<TaskDTO> createAndAddTaskToProject(TaskDTO taskDTO, Long projectId) {
        Project project = projectRepository.findProjectById(projectId);
        if (project == null) throw new EntityNotFoundException("There is no project with this id.");

        try {
            User user = userRepository.getOne((long) 1);
            user.setId(user.getId());
            user.setUsername(user.getUsername());
            Task task = taskMapper.taskDTOtoTask(taskDTO);
            task.setProject(project);
            task.setUser(user);
            Task task1 = taskRepository.save(task);
            project.addTask(task1);
            Project project1 = projectRepository.save(project);
            Set<TaskDTO> taskSet = getTasksByUserAdmin();

            return taskSet;
        } catch (Exception e) {
            throw e;
        }
    }
    // Method for deleting task by id
    @Override
    @Transactional
    public Set<TaskDTO> deleteTaskById(Integer taskId) {
        try {
            Task task = taskRepository.getTasksById(taskId);
            if (task == null) throw new EntityNotFoundException("There is no task with this id.");
            taskRepository.delete(task);

            Set<TaskDTO> taskSet = getTasksByUserAdmin();
            return taskSet;
        } catch (Exception e) {
            throw e;
        }
    }

//
}
