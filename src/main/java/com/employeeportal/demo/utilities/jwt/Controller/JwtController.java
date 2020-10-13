package com.employeeportal.demo.utilities.jwt.Controller;

import com.employeeportal.demo.user.config.MyUserDetails;
import com.employeeportal.demo.user.dto.UserResponseDTO;
import com.employeeportal.demo.utilities.jwt.Service.JwtUserDetailsService;
import com.employeeportal.demo.utilities.jwt.dto.JwtRequestDTO;
import com.employeeportal.demo.utilities.jwt.dto.JwtResponseDTO;
import com.employeeportal.demo.utilities.jwt.jwtconfig.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;


@RestController
public class JwtController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailsService;

    @RequestMapping(value = "/authenticate", method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequestDTO authenticationRequest) throws Exception {

        authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        final MyUserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String token = jwtTokenUtil.generateToken(userDetails);

        UserResponseDTO userResponseDTO = new UserResponseDTO();
        userResponseDTO.setName(userDetails.getUser().getName());
        userResponseDTO.setAddress(userDetails.getUser().getAddress());
        userResponseDTO.setEmail(userDetails.getUser().getEmail());
        userResponseDTO.setMusic(userDetails.getUser().getMusic());

        return ResponseEntity.ok(new JwtResponseDTO(token,userResponseDTO));
    }

    private void authenticate(String username, String password) throws Exception {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        } catch (DisabledException e) {
            throw new Exception("USER_DISABLED", e);
        } catch (BadCredentialsException e) {
            throw new Exception("INVALID_CREDENTIALS", e);
        }
    }
}
