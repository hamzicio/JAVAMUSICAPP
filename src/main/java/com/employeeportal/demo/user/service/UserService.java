package com.employeeportal.demo.user.service;

import com.employeeportal.demo.music.service.MusicService;
import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.exception.AddNewUserException;
import com.employeeportal.demo.user.exception.UserNotFoundException;
import com.employeeportal.demo.user.mapper.UserMapper;
import com.employeeportal.demo.user.repository.UserRepository;
import com.employeeportal.demo.user.dto.AddUserDTO;
import com.employeeportal.demo.user.dto.UserResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers()
    {
        List<User> users = userRepository.findAll();
        return users;
    }

    public UserResponseDTO addUser(AddUserDTO addUserDTO)  {

            User userExists= userRepository.getEmployeeByUsername(addUserDTO.getUsername());

            if(userExists != null)
            {
                return null;
            }

            User user = UserMapper.toDomain(addUserDTO);
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12); // Strength set as 12
            user.setPassword(encoder.encode(addUserDTO.getPassword()));
            userRepository.save(user);

            UserResponseDTO userResponseDTO = UserMapper.toDto(user);
            return userResponseDTO;
        }


    public UserResponseDTO getUserById(Integer id) {
        User user = userRepository.getEmployeeById(id);
        if(user == null)
        {
            return null;
        }
        return UserMapper.toDto(user);
    }

    public void deleteUserById(Integer id) throws UserNotFoundException {
        Optional<User> user = userRepository.findById(id);

        if(user == null)
        {
            throw new UserNotFoundException("User not found with this ID");
        }

        userRepository.deleteById(id);
    }

}
