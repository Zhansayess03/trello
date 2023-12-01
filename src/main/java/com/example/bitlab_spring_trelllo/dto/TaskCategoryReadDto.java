package com.example.bitlab_spring_trelllo.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCategoryReadDto {

    private Long id;
    private String name;
}
