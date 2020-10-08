package com.employeeportal.demo.music.controller;

import com.employeeportal.demo.music.dto.AddMusicDTO;
import com.employeeportal.demo.music.dto.MusicResponseDTO;
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
    public ResponseEntity<List> getAllMusic()
    {
        return ResponseEntity.ok(musicService.getAllMusic());
    }

    @PreAuthorize("hasAuthority('USER')")
    @GetMapping("getById/{id}")
    public ResponseEntity<MusicResponseDTO> getMusicById(@PathVariable Integer id) throws MusicDoesNotExistException, AccessDeniedException {
        MusicResponseDTO musicResponseDTO= musicService.getMusicById(id);


        Link deleteById= linkTo(methodOn(MusicController.class)
                .deleteMusicById(id)).withSelfRel();

        musicResponseDTO.add(deleteById);

        return ResponseEntity.ok(musicResponseDTO);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN','USER')")
    @DeleteMapping("deleteById/{id}")
    public ResponseEntity<String> deleteMusicById(@PathVariable Integer id) throws AccessDeniedException, MusicDoesNotExistException {
        musicService.deleteMusicById(id);
        return ResponseEntity.ok("Music Deleted");
    }

    @PreAuthorize("hasAuthority('USER')")
    @PostMapping("addMusic")
    public ResponseEntity<MusicResponseDTO> addMusic(@Valid @RequestBody AddMusicDTO addMusicDTO) throws UserNotFoundException
    {
        MusicResponseDTO musicResponseDTO=musicService.addMusic(addMusicDTO);

        if(musicResponseDTO==null)
        {
            throw new UserNotFoundException("User does not exist");

        }

        return ResponseEntity.ok(musicResponseDTO);
    }

}
