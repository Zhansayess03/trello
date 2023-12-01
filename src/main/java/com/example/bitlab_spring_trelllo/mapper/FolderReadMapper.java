package com.example.bitlab_spring_trelllo.mapper;
import com.example.bitlab_spring_trelllo.dto.FolderReadDto;
import com.example.bitlab_spring_trelllo.dto.TaskCategoryReadDto;
import com.example.bitlab_spring_trelllo.model.Folder;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class FolderReadMapper implements Mapper<Folder, FolderReadDto> {

    private final TaskCategoryReadMapper taskCategoryReadMapper;

    @Override
    public FolderReadDto map(Folder object) {
        List<TaskCategoryReadDto> taskCategories = Optional.ofNullable(object.getCategories())
                .stream()
                .flatMap(Collection::stream)
                .map(taskCategoryReadMapper::map)
                .toList();
        return new FolderReadDto(
                object.getId(),
                object.getName(),
                taskCategories
        );
    }
}