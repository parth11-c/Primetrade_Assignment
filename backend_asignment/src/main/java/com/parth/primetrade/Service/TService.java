package com.parth.primetrade.Service;

import com.parth.primetrade.Entity.Task;
import com.parth.primetrade.Entity.User;
import com.parth.primetrade.Repo.TaskRepo;
import com.parth.primetrade.Repo.UserRepo;
import com.parth.primetrade.dto.TaskRequest;
import com.parth.primetrade.dto.TaskResponse;
import com.parth.primetrade.util.SecurityUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class TService {

        private final TaskRepo taskRepository;
        private final UserRepo userRepository;

        public TService(TaskRepo taskRepository, UserRepo userRepository) {
                this.taskRepository = taskRepository;
                this.userRepository = userRepository;
        }

        public TaskResponse createTask(TaskRequest request) {
                String email = SecurityUtils.getCurrentUserEmail();
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Task task = Task.builder()
                                .title(request.getTitle())
                                .description(request.getDescription())
                                .status(request.getStatus())
                                .userId(user.getId())
                                .createdAt(LocalDateTime.now())
                                .build();

                @SuppressWarnings("null")
                Task saved = taskRepository.save(task);
                return mapToResponse(saved);
        }

        public List<TaskResponse> getAllTasks() {
                String email = SecurityUtils.getCurrentUserEmail();
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                return taskRepository.findByUserId(user.getId())
                                .stream()
                                .map(this::mapToResponse)
                                .toList();
        }

        public TaskResponse updateTask(String id, TaskRequest request) {
                String email = SecurityUtils.getCurrentUserEmail();
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Task task = taskRepository.findByIdAndUserId(id, user.getId())
                                .orElseThrow(() -> new RuntimeException("Task not found"));

                task.setTitle(request.getTitle());
                task.setDescription(request.getDescription());
                task.setStatus(request.getStatus());

                @SuppressWarnings("null")
                Task updated = taskRepository.save(task);
                return mapToResponse(updated);
        }

        public void deleteTask(String id) {
                String email = SecurityUtils.getCurrentUserEmail();
                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Task task = taskRepository.findByIdAndUserId(id, user.getId())
                                .orElseThrow(() -> new RuntimeException("Task not found"));

                taskRepository.delete(task);
        }

        private TaskResponse mapToResponse(Task task) {
                return TaskResponse.builder()
                                .id(task.getId())
                                .title(task.getTitle())
                                .description(task.getDescription())
                                .status(task.getStatus())
                                .createdAt(task.getCreatedAt())
                                .build();
        }
}
