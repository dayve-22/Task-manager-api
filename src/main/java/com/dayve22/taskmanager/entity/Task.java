package com.dayve22.taskmanager.entity;


import com.dayve22.taskmanager.enums.Priority;
import com.dayve22.taskmanager.enums.TaskStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "tasks")
@Data // Lombok: auto-generates getters, setters, toString, equals, hashCode
@NoArgsConstructor // Lombok: auto-generates a no-argument constructor
@AllArgsConstructor // Lombok: auto-generates a constructor with all arguments
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(min = 3, max = 100, message = "Title must be between 3 and 100 characters")
    @Column(nullable = false, length = 100)
    private String title;

    @Size(max = 255, message = "Description can be at most 255 characters")
    @Column(length = 255)
    private String description;

    @NotNull(message = "Status is required")
    @Enumerated(EnumType.STRING) // Stores the enum as a String (e.g., "PENDING")
    @Column(nullable = false)
    private TaskStatus status;

    @NotNull(message = "Priority is required")
    @Enumerated(EnumType.STRING) // Stores the enum as a String (e.g., "HIGH")
    @Column(nullable = false)
    private Priority priority;

    @FutureOrPresent(message = "Due date must be in the present or future")
    @Column
    private LocalDate dueDate;
}
