package com.example.bitlab_spring_trelllo.service;

import com.example.bitlab_spring_trelllo.dto.TaskCategoryCreateEditDto;
import com.example.bitlab_spring_trelllo.dto.TaskCategoryReadDto;


import java.util.List;

public interface TaskCategoryService {

    List<TaskCategoryReadDto> findAll();

    TaskCategoryReadDto findById(Long id);

    TaskCategoryReadDto create(TaskCategoryCreateEditDto task);

    TaskCategoryReadDto update(Long id, TaskCategoryCreateEditDto task);

    void delete(Long id);

}
