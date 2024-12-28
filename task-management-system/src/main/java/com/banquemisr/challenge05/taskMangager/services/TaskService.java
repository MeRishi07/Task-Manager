package com.banquemisr.challenge05.taskMangager.services;

import com.banquemisr.challenge05.taskMangager.Exeption.TaskException;
import com.banquemisr.challenge05.taskMangager.entity.History;
import com.banquemisr.challenge05.taskMangager.entity.Notification;
import com.banquemisr.challenge05.taskMangager.entity.Task;
import com.banquemisr.challenge05.taskMangager.entity.User;
import com.banquemisr.challenge05.taskMangager.enums.NotificationType;
import com.banquemisr.challenge05.taskMangager.enums.Priority;
import com.banquemisr.challenge05.taskMangager.enums.Status;
import com.banquemisr.challenge05.taskMangager.repository.HistoryRepo;
import com.banquemisr.challenge05.taskMangager.repository.NotificationRepo;
import com.banquemisr.challenge05.taskMangager.repository.TaskRepo;
import com.banquemisr.challenge05.taskMangager.repository.UserRepo;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
@AllArgsConstructor
public class TaskService {

    private final UserRepo userRepo;
    private final TaskRepo taskRepo;
    private final HistoryRepo historyRepo;
    private final NotificationRepo notificationRepo;

    public Task createTask(Long userId, Task task) {
        try {
            Optional<User> userOpt = userRepo.findById(userId);
            if (userOpt.isPresent()) {
                User user = userOpt.get();
                task.setUser(user);
                Task savedTask = taskRepo.save(task);

                logHistory("Task Created", user, savedTask);
                sendNotification(savedTask, "A new task has been assigned to you.", NotificationType.SYSTEM);

                log.info("Task created successfully: {}", savedTask);
                return savedTask;
            } else {
                throw new TaskException("User not found with ID: " + userId);
            }
        } catch (Exception e) {
            log.error("Error creating task", e);
            throw new RuntimeException("Unable to create task", e);
        }
    }
    public Optional<Task> getTaskByID(Long id) {
        return taskRepo.findById(id);
    }

    public List<Task> getTasksByUserID(Long userId) {
        return taskRepo.findByUserID(userId);
    }

    public List<Task> getAllTasks() {
        return taskRepo.findAll();
    }


    public void updateTask(Long id, Task updatedTask) {
        try {
            Optional<Task> existingTaskOpt = taskRepo.findById(id);
            if (existingTaskOpt.isPresent()) {
                Task existingTask = existingTaskOpt.get();

                existingTask.setTitle(updatedTask.getTitle());
                existingTask.setDescription(updatedTask.getDescription());
                existingTask.setStatus(updatedTask.getStatus());
                existingTask.setPriority(updatedTask.getPriority());
                existingTask.setDueDate(updatedTask.getDueDate());

                Task savedTask = taskRepo.save(existingTask);
                logHistory("Task Updated", existingTask.getUser(), savedTask);

                if (updatedTask.getDueDate().isAfter(ChronoLocalDate.from(LocalDateTime.now()))) {
                    sendNotification(savedTask, "Task updated: " + savedTask.getTitle(), NotificationType.SYSTEM);
                }

                log.info("Task updated successfully: {}", savedTask);
            } else {
                throw new TaskException("Task not found with ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error updating task", e);
            throw new RuntimeException("Unable to update task", e);
        }
    }

    public void deleteTask(Long id) {
        try {
            Optional<Task> taskOpt = taskRepo.findById(id);
            if (taskOpt.isPresent()) {
                Task task = taskOpt.get();
                taskRepo.delete(task);

                logHistory("Task Deleted", task.getUser(), task);
                sendNotification(task, "Task has been deleted: " + task.getTitle(), NotificationType.SYSTEM);

                log.info("Task deleted successfully with ID: {}", id);
            } else {
                throw new TaskException("Task not found with ID: " + id);
            }
        } catch (Exception e) {
            log.error("Error deleting task", e);
            throw new RuntimeException("Unable to delete task", e);
        }
    }

    private void logHistory(String action, User user, Task task) {
        try {
            History history = History.builder()
                    .actionType(action)
                    .timestamp(LocalDateTime.now())
                    .user(user)
                    .task(task)
                    .build();

            historyRepo.save(history);
            log.info("History logged: {}", history);
        } catch (Exception e) {
            log.error("Error logging history", e);
        }
    }

    private void sendNotification(Task task, String message, NotificationType notificationType) {
        try {
            Notification notification = Notification.builder()
                    .task(task)
                    .message(message)
                    .notificationType(notificationType)
                    .timestamp(LocalDateTime.now())
                    .build();

            notificationRepo.save(notification);
            log.info("Notification sent: {}", notification);
        } catch (Exception e) {
            log.error("Error sending notification", e);
        }
    }

    public List<Task> searchTasksByTitle(String title) {
        return taskRepo.findByTitleContaining(title);
    }
    public List<Task> searchTasksByStatus(Status status) {
        return taskRepo.findByStatus(status);
    }
    public List<Task> searchTasksByPriority(Priority priority) {
        return taskRepo.findByPriority(priority);
    }
    public List<Task> searchTasksByDueDate(Date date) {
        return taskRepo.findByDueDate(date);
    }
}