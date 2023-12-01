package com.example.bitlab_spring_trelllo.service;


import com.example.bitlab_spring_trelllo.dto.FolderCreateEditDto;
import com.example.bitlab_spring_trelllo.dto.FolderReadDto;


import java.util.List;

public interface FolderService {

    List<FolderReadDto> findAll();

    FolderReadDto findById(Long id);

    FolderReadDto create(FolderCreateEditDto folder);

    FolderReadDto update(Long id, FolderCreateEditDto folderDto);

    void delete(Long id);

    void assignCategory(Long folderId, Long categoryId);

    void unassignCategory(Long folderId, Long categoryId);

}
