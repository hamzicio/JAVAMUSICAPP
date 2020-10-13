package com.employeeportal.demo.UserTest;

import com.employeeportal.demo.user.dto.AddUserDTO;
import com.employeeportal.demo.user.dto.UserResponseDTO;
import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.mapper.UserMapper;
import org.junit.jupiter.api.Test;


import static org.junit.Assert.assertEquals;

public class UserControllerTest {


    @Test
    public void convertsUserDto()
    {
        AddUserDTO addUserDTO=new AddUserDTO();
        addUserDTO.setAddress("Test");
        addUserDTO.setEmail("test@test.com");
        addUserDTO.setName("Test");
        addUserDTO.setPassword("Test");
        addUserDTO.setUsername("Test");

        User user = UserMapper.toDomain(addUserDTO);

        UserResponseDTO userResponseDTO= UserMapper.toDto(user);

        assertEquals(userResponseDTO.getAddress(),(addUserDTO.getAddress()));






    }

}
