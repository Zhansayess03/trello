package com.example.bitlab_spring_trelllo.service;

import com.example.bitlab_spring_trelllo.dto.TaskCreateEditDto;
import com.example.bitlab_spring_trelllo.dto.TaskReadDto;
import java.util.List;

public interface TaskService {

    List<TaskReadDto> findAll();

    TaskReadDto findById(Long id);

    List<TaskReadDto> findByFolderId(Long folderId);

    TaskReadDto create(TaskCreateEditDto task);

    TaskReadDto update(Long id, TaskCreateEditDto task);

    void delete(Long id);

}
