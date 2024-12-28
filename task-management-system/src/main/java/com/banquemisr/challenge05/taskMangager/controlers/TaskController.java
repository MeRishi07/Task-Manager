package com.banquemisr.challenge05.taskMangager.controlers;

import com.banquemisr.challenge05.taskMangager.entity.Task;
import com.banquemisr.challenge05.taskMangager.enums.Priority;
import com.banquemisr.challenge05.taskMangager.enums.Status;
import com.banquemisr.challenge05.taskMangager.services.TaskService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/task")
@AllArgsConstructor
public class TaskController {



    private final TaskService taskService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Task>> getTasksByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(taskService.getTasksByUserID(userId));
    }
    @PostMapping("/user/{userId}")
    public ResponseEntity<Task> createTask(@PathVariable Long userId, @RequestBody Task task) {
        return ResponseEntity.ok(taskService.createTask(userId, task));
    }
    @PutMapping("/{id}")
    public void UpdateTask(@PathVariable Long taskId, @RequestBody Task task) {
        taskService.updateTask(taskId, task);
    }
    @GetMapping("/all")
    public ResponseEntity<List<Task>>  findAllTasks() {
        return ResponseEntity.ok(taskService.getAllTasks());
    }
    @GetMapping("/{id}")
    public ResponseEntity<Optional<Task>>  findTaskByID(@PathVariable("id") Long id) {
        return ResponseEntity.ok(taskService.getTaskByID(id));
    }
    @DeleteMapping("/{id}")
    public void deleteTaskByID( @PathVariable("id") Long id){
        taskService.deleteTask(id);
    }
    @GetMapping("/search/title")
    public ResponseEntity<List<Task>> searchTasksByTitle(@RequestParam(required = false) String title) {
        return ResponseEntity.ok(taskService.searchTasksByTitle(title));
    }
    @GetMapping("/search/status")
    public ResponseEntity<List<Task>> searchTasksByStatus(@RequestParam(required = false) Status status) {
        return ResponseEntity.ok(taskService.searchTasksByStatus( status));
    }
    @GetMapping("/search/priority")
    public ResponseEntity<List<Task>> searchTasksByPriority(@RequestParam(required = false) Priority priority) {
        return ResponseEntity.ok(taskService.searchTasksByPriority(priority));
    }
    @GetMapping("/search/date")
    public ResponseEntity<List<Task>> searchTasksByPriority(@RequestParam(required = false) Date date) {
        return ResponseEntity.ok(taskService.searchTasksByDueDate(date));
    }

}
