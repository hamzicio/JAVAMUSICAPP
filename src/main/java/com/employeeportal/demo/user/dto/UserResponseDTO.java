package com.employeeportal.demo.user.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO extends RepresentationModel<UserResponseDTO> {
    private String name;
    private String username;
    private String address;
    private String email;
}
