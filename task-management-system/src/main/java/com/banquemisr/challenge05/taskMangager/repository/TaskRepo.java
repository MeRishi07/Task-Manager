package com.banquemisr.challenge05.taskMangager.repository;

import com.banquemisr.challenge05.taskMangager.entity.Task;
import com.banquemisr.challenge05.taskMangager.enums.Priority;
import com.banquemisr.challenge05.taskMangager.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;


public interface TaskRepo extends JpaRepository<Task,Long> {
    List<Task> findByUserID(Long userId);

    List<Task> findByTitle(String title);
    
    List<Task> findByStatus(Status status);

    List<Task> findByTitleContaining(String title);

    List<Task> findByDueDate(Date dueDate);

    List<Task> findByPriority(Priority priority);
}
