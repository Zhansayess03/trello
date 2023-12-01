package com.example.bitlab_spring_trelllo.repository;

import com.example.bitlab_spring_trelllo.model.TaskCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskCategoryRepository extends JpaRepository<TaskCategory, Long> {
}
