package com.employeeportal.demo.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserDTO {

    @Column(unique = true,nullable = false)
    @NotNull(message = "Username is neccessary")
    private String username;

    @Column(nullable = false)
    @NotNull(message = "Name is neccessary")
    private String name;

    @Column(nullable = false)
    @NotNull(message = "Address is neccessary")
    private String address;

    @Column(nullable = false)
    @Size(min = 10,message = "Minimum 10 is length required for password")
    @NotNull(message = "Passowrd is neccessary")
    private String password;

    @Column(unique = true,nullable = false)
    @NotNull(message = "Email is neccessary")
    private String email;
}
