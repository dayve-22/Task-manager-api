package com.dayve22.taskmanager.repository;

import com.dayve22.taskmanager.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// We specify the Entity type (Task) and the type of its Primary Key (Long)
@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
}
