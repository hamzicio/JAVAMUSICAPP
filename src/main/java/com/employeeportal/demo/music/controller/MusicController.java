package com.employeeportal.demo.music.controller;

import com.employeeportal.demo.music.dto.AddMusicDTO;
import com.employeeportal.demo.music.dto.MusicResponseDTO;
import com.employeeportal.demo.music.dto.MusicWithPagingDTO;
import com.employeeportal.demo.music.exception.AccessDeniedException;
import com.employeeportal.demo.music.exception.MusicDoesNotExistException;
import com.employeeportal.demo.music.service.MusicService;
import com.employeeportal.demo.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Controller
@RequestMapping("/music/")
public class MusicController {

    @Autowired
    MusicService musicService;

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("getAll")
    public ResponseEntity<MusicWithPagingDTO> getAllMusic(@RequestParam(defaultValue = "0") Integer pageNo,
                                                          @RequestParam(defaultValue = "10") Integer pageSize)
    {
        return ResponseEntity.ok(musicService.getAllMusic(pageNo,pageSize));
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("getById")
    public ResponseEntity<MusicWithPagingDTO> getMusicById(@RequestParam(defaultValue = "0") Integer pageNo,
                                                         @RequestParam(defaultValue = "10") Integer pageSize) throws MusicDoesNotExistException, AccessDeniedException {
       MusicWithPagingDTO musicWithPagingDTO= musicService.getMusicByUsername(pageNo,pageSize);
        return ResponseEntity.ok(musicWithPagingDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<MusicWithPagingDTO> deleteMusicById(@PathVariable Integer id) throws AccessDeniedException, MusicDoesNotExistException {
        MusicWithPagingDTO musicWithPagingDTO=  musicService.deleteMusicById(id);

        if(musicWithPagingDTO==null)
        {
            throw new MusicDoesNotExistException("Music Not Found");

        }

        return ResponseEntity.ok(musicWithPagingDTO);
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("addMusic")
    public ResponseEntity<MusicWithPagingDTO> addMusic(@Valid @RequestBody AddMusicDTO addMusicDTO) throws UserNotFoundException, MusicDoesNotExistException {
        MusicWithPagingDTO musicWithPagingDTO=musicService.addMusic(addMusicDTO);

        if(musicWithPagingDTO==null)
        {
            throw new UserNotFoundException("User does not exist");

        }

        return ResponseEntity.ok(musicWithPagingDTO);
    }

}
