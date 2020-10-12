package com.employeeportal.demo.user.mapper;

import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.dto.AddUserDTO;
import com.employeeportal.demo.user.dto.UserResponseDTO;


public class UserMapper {

    public static UserResponseDTO toDto(User user)
    {
        UserResponseDTO userResponseDTO =new UserResponseDTO();
        userResponseDTO.setUsername(user.getUsername());
        userResponseDTO.setName(user.getName());
        userResponseDTO.setAddress(user.getAddress());
        userResponseDTO.setEmail(user.getEmail());

        return userResponseDTO;
    }

    public static User toDomain(AddUserDTO addUserDTO)
    {
        User user =new User();
        user.setName(addUserDTO.getName());
        user.setAddress(addUserDTO.getAddress());
        user.setEmail(addUserDTO.getEmail());
        user.setUsername(addUserDTO.getUsername());
        user.setPassword(addUserDTO.getPassword());


        return user;
    }

}
