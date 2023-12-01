package com.example.bitlab_spring_trelllo.mapper;
import com.example.bitlab_spring_trelllo.dto.TaskCategoryReadDto;
import com.example.bitlab_spring_trelllo.model.TaskCategory;
import org.springframework.stereotype.Component;

@Component
public class TaskCategoryReadMapper implements Mapper<TaskCategory, TaskCategoryReadDto> {

    @Override
    public TaskCategoryReadDto map(TaskCategory object) {
        return new TaskCategoryReadDto(
                object.getId(),
                object.getName()
        );
    }
}
