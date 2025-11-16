package com.dayve22.taskmanager.service;


import com.dayve22.taskmanager.dto.TaskRequestDto;
import com.dayve22.taskmanager.dto.TaskResponseDto;
import com.dayve22.taskmanager.entity.Task;
import com.dayve22.taskmanager.exception.TaskNotFoundException;
import com.dayve22.taskmanager.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public TaskResponseDto createTask(TaskRequestDto taskRequestDto) {
        Task task = mapToEntity(taskRequestDto);
        Task savedTask = taskRepository.save(task);
        return mapToResponseDto(savedTask);
    }

    @Override
    public TaskResponseDto getTaskById(Long id) {
        Task task = findTaskOrThrow(id);
        return mapToResponseDto(task);
    }

    @Override
    public List<TaskResponseDto> getAllTasks() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.stream()
                .map(this::mapToResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto) {
        // First, check if the task exists
        Task existingTask = findTaskOrThrow(id);

        // Update the existing task with new data
        existingTask.setTitle(taskRequestDto.getTitle());
        existingTask.setDescription(taskRequestDto.getDescription());
        existingTask.setStatus(taskRequestDto.getStatus());
        existingTask.setPriority(taskRequestDto.getPriority());
        existingTask.setDueDate(taskRequestDto.getDueDate());

        Task updatedTask = taskRepository.save(existingTask);
        return mapToResponseDto(updatedTask);
    }

    @Override
    public void deleteTask(Long id) {
        // Check if the task exists before deleting
        Task task = findTaskOrThrow(id);
        taskRepository.delete(task);
    }


    private Task findTaskOrThrow(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Task not found with id: " + id));
    }


    private Task mapToEntity(TaskRequestDto dto) {
        Task task = new Task();
        task.setTitle(dto.getTitle());
        task.setDescription(dto.getDescription());
        task.setStatus(dto.getStatus());
        task.setPriority(dto.getPriority());
        task.setDueDate(dto.getDueDate());
        return task;
    }

    /**
     * Maps a Task entity to a TaskResponseDto.
     */
    private TaskResponseDto mapToResponseDto(Task task) {
        TaskResponseDto dto = new TaskResponseDto();
        dto.setId(task.getId());
        dto.setTitle(task.getTitle());
        dto.setDescription(task.getDescription());
        dto.setStatus(task.getStatus());
        dto.setPriority(task.getPriority());
        dto.setDueDate(task.getDueDate());
        return dto;
    }
}
