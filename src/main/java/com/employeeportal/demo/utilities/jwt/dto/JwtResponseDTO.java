package com.employeeportal.demo.utilities.jwt.dto;

import com.employeeportal.demo.user.dto.UserResponseDTO;
import com.employeeportal.demo.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.io.Serializable;

@Getter
@AllArgsConstructor
public class JwtResponseDTO implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;
    private final UserResponseDTO user;
}
