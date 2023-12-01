package com.example.bitlab_spring_trelllo.service.impl;

import com.example.bitlab_spring_trelllo.dto.TaskCreateEditDto;
import com.example.bitlab_spring_trelllo.dto.TaskReadDto;
import com.example.bitlab_spring_trelllo.mapper.TaskCreateEditMapper;
import com.example.bitlab_spring_trelllo.mapper.TaskReadMapper;
import com.example.bitlab_spring_trelllo.repository.TaskRepository;
import com.example.bitlab_spring_trelllo.service.TaskService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskReadMapper taskReadMapper;
    private final TaskCreateEditMapper taskCreateEditMapper;

    @Override
    public List<TaskReadDto> findAll() {
        return taskRepository.findAll().stream()
                .map(taskReadMapper::map)
                .toList();
    }

    @Override
    public TaskReadDto findById(Long id) {
        return taskRepository.findById(id)
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Override
    public List<TaskReadDto> findByFolderId(Long folderId) {
        return taskRepository.findByFolderId(folderId).stream()
                .map(taskReadMapper::map)
                .toList();
    }

    @Transactional
    @Override
    public TaskReadDto create(TaskCreateEditDto task) {
        return Optional.of(task)
                .map(taskCreateEditMapper::map)
                .map(taskRepository::save)
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public TaskReadDto update(Long id, TaskCreateEditDto taskDto) {
        return taskRepository.findById(id)
                .map(entity -> taskCreateEditMapper.map(taskDto, entity))
                .map(taskReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        taskRepository.findById(id)
                .ifPresent(entity -> taskRepository.deleteById(id));
    }
}
