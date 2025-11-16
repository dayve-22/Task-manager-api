package com.dayve22.taskmanager.controller;

import com.dayve22.taskmanager.dto.TaskRequestDto;
import com.dayve22.taskmanager.entity.Task;
import com.dayve22.taskmanager.enums.Priority;
import com.dayve22.taskmanager.enums.TaskStatus;
import com.dayve22.taskmanager.repository.TaskRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.time.LocalDate;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

// Loads the full Spring context and auto-configures MockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@AutoConfigureMockMvc
public class TaskControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private TaskRepository taskRepository;

    // ObjectMapper is used to convert Java objects to JSON and vice-versa
    private final ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        // Clear the database before each test
        taskRepository.deleteAll();

        // Register the JavaTimeModule to handle LocalDate correctly
        objectMapper.registerModule(new JavaTimeModule());
    }

    /**
     * Test Case 1: Create Task (Success)
     */
    @Test
    public void givenTaskRequest_whenCreateTask_thenReturn201Created() throws Exception {
        // 1. Arrange
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setTitle("Complete Spring Boot Assignment");
        requestDto.setDescription("Build a task management API");
        requestDto.setStatus(TaskStatus.PENDING);
        requestDto.setPriority(Priority.HIGH);
        requestDto.setDueDate(LocalDate.parse("2024-02-15"));

        // 2. Act
        ResultActions response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        // 3. Assert
        response.andExpect(status().isCreated()) // Expected Result: 201 Created
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.title", is("Complete Spring Boot Assignment")))
                .andExpect(jsonPath("$.status", is("PENDING")));
    }

    /**
     * Test Case 2: Validation Error
     */
    @Test
    public void givenInvalidTaskRequest_whenCreateTask_thenReturn400BadRequest() throws Exception {
        // 1. Arrange
        TaskRequestDto requestDto = new TaskRequestDto();
        requestDto.setTitle("Hi"); // Fails @Size(min=3)
        requestDto.setPriority(null); // Fails @NotNull
        // Status is also missing, which fails @NotNull

        // 2. Act
        ResultActions response = mockMvc.perform(post("/api/tasks")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestDto)));

        // 3. Assert
        response.andExpect(status().isBadRequest()) // Expected Result: 400 Bad Request
                .andExpect(jsonPath("$.errors.title", is("Title must be between 3 and 100 characters")))
                .andExpect(jsonPath("$.errors.priority", is("Priority is required")))
                .andExpect(jsonPath("$.errors.status", is("Status is required")));
    }

    /**
     * Test Case 3: Get Non-existent Task
     */
    @Test
    public void givenInvalidTaskId_whenGetTaskById_thenReturn404NotFound() throws Exception {
        // 1. Arrange
        Long nonExistentId = 999L;

        // 2. Act
        ResultActions response = mockMvc.perform(get("/api/tasks/{id}", nonExistentId));

        // 3. Assert
        response.andExpect(status().isNotFound()) // Expected Result: 404 Not Found
                .andExpect(jsonPath("$.message", is("Task not found with id: " + nonExistentId)));
    }

    @Test
    public void givenValidTaskId_whenGetTaskById_thenReturn200Ok() throws Exception {
        // 1. Arrange
        Task savedTask = taskRepository.save(new Task(null, "Existing Task", "Desc", TaskStatus.COMPLETED, Priority.LOW, null));

        // 2. Act
        ResultActions response = mockMvc.perform(get("/api/tasks/{id}", savedTask.getId()));

        // 3. Assert
        response.andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(savedTask.getId().intValue())))
                .andExpect(jsonPath("$.title", is("Existing Task")));
    }
}
