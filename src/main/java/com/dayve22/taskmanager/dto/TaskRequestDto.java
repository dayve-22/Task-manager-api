package com.dayve22.taskmanager.dto;

import com.dayve22.taskmanager.enums.Priority;
import com.dayve22.taskmanager.enums.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDate;

// Lombok @Data generates getters, setters, toString, etc.
@Data
public class TaskRequestDto {

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    private String title;

    @Size(max = 255, message = "Description can be at most 255 characters")
    private String description;

    @NotNull(message = "Status is required")
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    private Priority priority;

    @FutureOrPresent(message = "Due date must be in the present or future")
    private LocalDate dueDate;
}
