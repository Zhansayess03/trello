package com.example.bitlab_spring_trelllo.service.impl;

import com.example.bitlab_spring_trelllo.dto.FolderCreateEditDto;
import com.example.bitlab_spring_trelllo.dto.FolderReadDto;
import com.example.bitlab_spring_trelllo.mapper.FolderCreateEditMapper;
import com.example.bitlab_spring_trelllo.mapper.FolderReadMapper;
import com.example.bitlab_spring_trelllo.model.Folder;
import com.example.bitlab_spring_trelllo.model.Task;
import com.example.bitlab_spring_trelllo.model.TaskCategory;
import com.example.bitlab_spring_trelllo.repository.FolderRepository;
import com.example.bitlab_spring_trelllo.repository.TaskCategoryRepository;
import com.example.bitlab_spring_trelllo.repository.TaskRepository;
import com.example.bitlab_spring_trelllo.service.FolderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class FolderServiceImpl implements FolderService {

    private final FolderRepository folderRepository;
    private final TaskCategoryRepository taskCategoryRepository;
    private final TaskRepository taskRepository;

    private final FolderReadMapper folderReadMapper;
    private final FolderCreateEditMapper folderCreateEditMapper;

    @Override
    public List<FolderReadDto> findAll() {
        return folderRepository.findAll().stream()
                .map(folderReadMapper::map)
                .toList();
    }

    @Override
    public FolderReadDto findById(Long id) {
        return folderRepository.findById(id)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public FolderReadDto create(FolderCreateEditDto folder) {
        return Optional.of(folder)
                .map(folderCreateEditMapper::map)
                .map(folderRepository::save)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public FolderReadDto update(Long id, FolderCreateEditDto folderDto) {
        return folderRepository.findById(id)
                .map(entity -> folderCreateEditMapper.map(folderDto, entity))
                .map(folderRepository::saveAndFlush)
                .map(folderReadMapper::map)
                .orElseThrow();
    }

    @Transactional
    @Override
    public void delete(Long id) {
        for(Task task : taskRepository.findAll()){
            if(task.getFolder().getId()==id){
                taskRepository.deleteById(task.getId());
            }
        }
        folderRepository.deleteById(id);
    }

    @Transactional
    @Override
    public void assignCategory(Long folderId, Long categoryId) {
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(categoryId);

        optionalFolder.ifPresent(folder -> {
            optionalTaskCategory.ifPresent(taskCategory -> {
                List<TaskCategory> folderCategories = folder.getCategories();
                if (folderCategories == null) {
                    folderCategories = new ArrayList<>();
                }
                if (!folderCategories.contains(taskCategory)) {
                    folderCategories.add(taskCategory);
                    folder.setCategories(folderCategories);
                    folderRepository.save(folder);
                }
            });
        });
    }

    @Transactional
    @Override
    public void unassignCategory(Long folderId, Long categoryId) {
        Optional<Folder> optionalFolder = folderRepository.findById(folderId);
        Optional<TaskCategory> optionalTaskCategory = taskCategoryRepository.findById(categoryId);

        optionalFolder.ifPresent(folder -> {
            optionalTaskCategory.ifPresent(taskCategory -> {
                List<TaskCategory> folderCategories = folder.getCategories();
                if (folderCategories == null) {
                    folderCategories = new ArrayList<>();
                }
                if (folderCategories.contains(taskCategory)) {
                    folderCategories.remove(taskCategory);
                    folder.setCategories(folderCategories);
                    folderRepository.save(folder);
                }
            });
        });
    }
}
