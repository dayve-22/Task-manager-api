package com.dayve22.taskmanager.dto;

import com.dayve22.taskmanager.enums.Priority;
import com.dayve22.taskmanager.enums.TaskStatus;
import lombok.Data;

import java.time.LocalDate;

@Data
public class TaskResponseDto {

    private Long id;
    private String title;
    private String description;
    private TaskStatus status;
    private Priority priority;
    private LocalDate dueDate;
}
