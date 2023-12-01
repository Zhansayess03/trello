package com.example.bitlab_spring_trelllo.mapper;

public interface Mapper<F, T> {

    T map(F object);

    default T map(F fromObject, T toObject) {
        return toObject;
    }
}
