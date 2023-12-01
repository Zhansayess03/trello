package com.example.bitlab_spring_trelllo.mapper;
import com.example.bitlab_spring_trelllo.dto.TaskCategoryCreateEditDto;
import com.example.bitlab_spring_trelllo.model.TaskCategory;

import org.springframework.stereotype.Component;

@Component
public class TaskCategoryCreateEditMapper implements Mapper<TaskCategoryCreateEditDto, TaskCategory> {

    @Override
    public TaskCategory map(TaskCategoryCreateEditDto fromObject, TaskCategory toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public TaskCategory map(TaskCategoryCreateEditDto object) {
        TaskCategory taskCategory = new TaskCategory();
        copy(object, taskCategory);
        return taskCategory;
    }

    private void copy(TaskCategoryCreateEditDto object, TaskCategory taskCategory) {
        taskCategory.setName(object.getName());
    }
}
