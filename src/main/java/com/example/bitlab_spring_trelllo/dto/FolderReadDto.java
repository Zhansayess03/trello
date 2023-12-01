package com.example.bitlab_spring_trelllo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FolderReadDto {

    private Long id;
    private String name;
    private List<TaskCategoryReadDto> categories;
}
