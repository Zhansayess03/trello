package com.example.bitlab_spring_trelllo.mapper;

import com.example.bitlab_spring_trelllo.dto.UsersDto;
import com.example.bitlab_spring_trelllo.model.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto mapToUsersDto(User user);
    List<UsersDto> mapToUsersDtoList(List<User> userList);
    User mapToUsers(UsersDto usersDto);
    List<User> mapToUsersList(List<UsersDto> usersDtoList);

}
