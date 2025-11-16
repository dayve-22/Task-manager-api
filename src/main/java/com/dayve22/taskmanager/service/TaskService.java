package com.dayve22.taskmanager.service;

import com.dayve22.taskmanager.dto.TaskRequestDto;
import com.dayve22.taskmanager.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    TaskResponseDto createTask(TaskRequestDto taskRequestDto);

    TaskResponseDto getTaskById(Long id);

    List<TaskResponseDto> getAllTasks();

    TaskResponseDto updateTask(Long id, TaskRequestDto taskRequestDto);

    void deleteTask(Long id);
}