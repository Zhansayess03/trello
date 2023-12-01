package com.example.bitlab_spring_trelllo.repository;

import com.example.bitlab_spring_trelllo.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByFolderId(Long folderId);
}
