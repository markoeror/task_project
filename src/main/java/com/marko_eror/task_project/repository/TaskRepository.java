package com.marko_eror.task_project.repository;

import com.marko_eror.task_project.enums.TaskStatus;
import com.marko_eror.task_project.model.Task;
import com.marko_eror.task_project.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;
@Repository
public interface TaskRepository extends JpaRepository<Task,String> {

    Set<Task> findTaskByUser(User user);
    @Query("select t from Task t where t.user.id in(:id,1)")
    Set<Task> findTasksByUserId(@Param("id") long id);
//    @Query("select u from User u left join fetch u.projects p left join fetch p.tasks where u.id = :userId")
//    Set<Task> getTasksByUserid(@Param("userId")Long userId);
//
//    @Query("update Task t set t.taskStatus=:status where  t.id=:id")
//    Task updateTaskStatus(@Param("status") TaskStatus status,@Param("id") Long id);

    Task getTasksById(long id);

    Task findTaskById(Long id);
}
