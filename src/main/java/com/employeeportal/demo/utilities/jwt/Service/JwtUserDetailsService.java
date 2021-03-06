package com.employeeportal.demo.utilities.jwt.Service;

import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.repository.UserRepository;
import com.employeeportal.demo.user.config.MyUserDetails;
import com.employeeportal.demo.utilities.jwt.exception.BadCredentialException;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @SneakyThrows
    @Override
    public MyUserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        User user = userRepository.getUserByUsername(username);

        if (user == null) {
            throw new BadCredentialException("Could not find user");
        }

        return new MyUserDetails(user);
    }
}

