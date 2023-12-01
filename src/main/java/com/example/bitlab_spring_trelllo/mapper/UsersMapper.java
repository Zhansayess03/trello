package com.example.bitlab_spring_trelllo.mapper;

import com.example.bitlab_spring_trelllo.dto.UsersDto;
import com.example.bitlab_spring_trelllo.model.Users;
import org.apache.catalina.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsersMapper {
    UsersDto mapToUsersDto(Users users);
    List<UsersDto> mapToUsersDtoList(List<Users> usersList);
    Users mapToUsers(UsersDto usersDto);
    List<Users> mapToUsersList(List<UsersDto> usersDtoList);

}
