package com.employeeportal.demo.user.controller;

import com.employeeportal.demo.user.exception.AddNewUserException;
import com.employeeportal.demo.user.exception.UserNotFoundException;
import com.employeeportal.demo.user.service.UserService;
import com.employeeportal.demo.user.dto.AddUserDTO;
import com.employeeportal.demo.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/user/")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable Integer id) throws UserNotFoundException {
        userService.deleteUserById(id);
        return ResponseEntity.ok("User Deleted");
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("getById/{id}")
    public ResponseEntity<UserResponseDTO>  getUserById(@PathVariable Integer id) throws UserNotFoundException {
        UserResponseDTO userResponseDTO = userService.getUserById(id);

        if(userResponseDTO ==null)
        {
            throw new UserNotFoundException("User not found for id " + id);
        }

        Link getById = linkTo(methodOn(UserController.class)
                .getUserById(id)).withSelfRel();

        Link deleteById= linkTo(methodOn(UserController.class)
                .deleteUserById(id)).withRel("deleteUser");

        Link getAllUsers= linkTo(methodOn(UserController.class)
                .getAllUsers()).withRel("getAllUsers");

        userResponseDTO.add(getById);
        userResponseDTO.add(deleteById);
        userResponseDTO.add(getAllUsers);

        return ResponseEntity.ok(userResponseDTO);
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("getAll")
    public ResponseEntity<List> getAllUsers()
    {
        return ResponseEntity.ok(userService.getAllUsers());
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("addUser")
    public ResponseEntity<UserResponseDTO>  addEmploy(@Valid @RequestBody AddUserDTO addUserDTO) throws AddNewUserException {
        UserResponseDTO userResponseDTO = userService.addUser(addUserDTO);

        if (userResponseDTO == null) {
            throw  new AddNewUserException("User already exists");
        }
        return ResponseEntity.ok(userResponseDTO);
    }


}
