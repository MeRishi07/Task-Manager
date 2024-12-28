//package com.banquemisr.challenge05.taskMangager;
//
//import com.banquemisr.challenge05.taskMangager.Exeption.TaskException;
//import com.banquemisr.challenge05.taskMangager.entity.Task;
//import com.banquemisr.challenge05.taskMangager.entity.User;
//import com.banquemisr.challenge05.taskMangager.repository.TaskRepo;
//import com.banquemisr.challenge05.taskMangager.repository.UserRepo;
//import com.banquemisr.challenge05.taskMangager.services.TaskService;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.Optional;
//
//@ExtendWith(MockitoExtension.class)
//public class TaskServiceTest {
//
//    @Mock
//    private TaskRepo taskRepo;
//
//    @Mock
//    private UserRepo userRepo;
//
//    @InjectMocks
//    private TaskService taskService;
//
//    @Test
//    void testCreateTask_Success() {
//        // Arrange
//        Long userId = 1L;
//        User user = new User(userId, "John Doe", "johndoe", "password", "USER", "john@example.com");
//        Task task = new Task(1L, "Task Title", "Task Description", "OPEN", 1, null, null);
//
//        Mockito.when(userRepo.findById(userId)).thenReturn(Optional.of(user));
//        Mockito.when(taskRepo.save(task)).thenReturn(task);
//
//        // Act
//        Task result = taskService.createTask(userId, task);
//
//        // Assert
//        Assertions.assertNotNull(result);
//        Assertions.assertEquals("Task Title", result.getTitle());
//        Assertions.assertEquals(user, result.getUser());
//    }
//
//    @Test
//    void testGetTaskByID_Success() {
//        // Arrange
//        Long taskId = 1L;
//        Task task = new Task(taskId, "Task Title", "Task Description", "OPEN", 1, null, null);
//
//        Mockito.when(taskRepo.findById(taskId)).thenReturn(Optional.of(task));
//
//        // Act
//        Optional<Task> result = taskService.getTaskByID(taskId);
//
//        // Assert
//        Assertions.assertTrue(result.isPresent());
//        Assertions.assertEquals(taskId, result.get().getId());
//    }
//
//    @Test
//    void testDeleteTask_Success() {
//        // Arrange
//        Long taskId = 1L;
//        Mockito.when(taskRepo.existsById(taskId)).thenReturn(true);
//
//        // Act
//        taskService.deleteTask(taskId);
//
//        // Assert
//        Mockito.verify(taskRepo, Mockito.times(1)).deleteById(taskId);
//    }
//
//    @Test
//    void testDeleteTask_NotFound() {
//        // Arrange
//        Long taskId = 1L;
//        Mockito.when(taskRepo.existsById(taskId)).thenReturn(false);
//
//        // Act & Assert
//        TaskException exception = Assertions.assertThrows(TaskException.class, () -> taskService.deleteTask(taskId));
//        Assertions.assertEquals("Task not found with ID: " + taskId, exception.getMessage());
//    }
//}