package com.dayve22.taskmanager.repository;

import com.dayve22.taskmanager.entity.Task;
import com.dayve22.taskmanager.enums.Priority;
import com.dayve22.taskmanager.enums.TaskStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class TaskRepositoryTest {

    @Autowired
    private TestEntityManager entityManager; // Helper for persisting data in tests

    @Autowired
    private TaskRepository taskRepository;

    @Test
    public void whenFindById_thenReturnTask() {
        // 1. Arrange
        Task task = new Task();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setStatus(TaskStatus.PENDING);
        task.setPriority(Priority.MEDIUM);
        task.setDueDate(LocalDate.now().plusDays(1));

        // Persist the task to the in-memory H2 database
        entityManager.persist(task);
        entityManager.flush(); // Force save to DB

        // 2. Act
        Optional<Task> foundTask = taskRepository.findById(task.getId());

        // 3. Assert
        assertThat(foundTask.isPresent()).isTrue();
        assertThat(foundTask.get().getTitle()).isEqualTo(task.getTitle());
    }

    @Test
    public void whenInvalidId_thenReturnEmpty() {
        // 1. Arrange
        // No data inserted

        // 2. Act
        Optional<Task> foundTask = taskRepository.findById(999L);

        // 3. Assert
        assertThat(foundTask.isPresent()).isFalse();
    }

    @Test
    public void whenSave_thenPersistTask() {
        // 1. Arrange
        Task task = new Task(null, "New Task", "Desc", TaskStatus.IN_PROGRESS, Priority.LOW, null);

        // 2. Act
        Task savedTask = taskRepository.save(task);

        // 3. Assert
        assertThat(savedTask).isNotNull();
        assertThat(savedTask.getId()).isGreaterThan(0);
        assertThat(savedTask.getTitle()).isEqualTo("New Task");
    }
}
