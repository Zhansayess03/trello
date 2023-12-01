package com.example.bitlab_spring_trelllo.mapper;


import com.example.bitlab_spring_trelllo.dto.TaskCreateEditDto;
import com.example.bitlab_spring_trelllo.model.Folder;
import com.example.bitlab_spring_trelllo.model.Task;
import com.example.bitlab_spring_trelllo.repository.FolderRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TaskCreateEditMapper implements Mapper<TaskCreateEditDto, Task> {

    private static final Integer DEFAULT_VALUE = 0;
    private final FolderRepository folderRepository;

    @Override
    public Task map(TaskCreateEditDto fromObject, Task toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Task map(TaskCreateEditDto object) {
        Task task = new Task();
        copy(object, task);
        return task;
    }

    private void copy(TaskCreateEditDto object, Task task) {
        task.setTitle(object.getTitle());
        task.setDescription(object.getDescription());
        task.setStatus(defineStatus(object.getStatus()));
        task.setFolder(getFolder(object.getFolderId()));
    }

    private Integer defineStatus(Integer status) {
        return status == null ? DEFAULT_VALUE : status;
    }

    private Folder getFolder(Long folderId) {
        return Optional.ofNullable(folderId)
                .flatMap(folderRepository::findById)
                .orElse(null);
    }
}
