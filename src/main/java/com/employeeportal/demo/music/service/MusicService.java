package com.employeeportal.demo.music.service;

import com.employeeportal.demo.music.dto.AddMusicDTO;
import com.employeeportal.demo.music.dto.MusicResponseDTO;
import com.employeeportal.demo.music.exception.AccessDeniedException;
import com.employeeportal.demo.music.exception.MusicDoesNotExistException;
import com.employeeportal.demo.music.mapper.MusicMapper;
import com.employeeportal.demo.music.repository.MusicRepository;
import com.employeeportal.demo.music.entity.Music;
import com.employeeportal.demo.user.config.IAuthenticationFacade;
import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class MusicService {

    @Autowired
    private MusicRepository musicRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private IAuthenticationFacade authenticationFacade;


    public List<Music> getAllMusic()
    {
        List<Music> musicList =musicRepository.findAll();
        return musicList;
    }

    public void deleteMusicById(Integer id) throws AccessDeniedException, MusicDoesNotExistException {
        Object principal= authenticationFacade.getAuthentication().getPrincipal();
        if(principal instanceof UserDetails)
        {
            String username=((UserDetails) principal).getUsername();
            Music music= musicRepository.getMusicById(id);

            if(music != null)
            {
                if (!(music.getUser().getUsername().equals(username)))
                    {
                        throw new AccessDeniedException("You cant delete this music track");
                     }
                this.musicRepository.deleteById(id);
            }
            else
            {
                throw new MusicDoesNotExistException("Music not found with this id does not exist");
            }

        }
    }

    public MusicResponseDTO getMusicById(Integer id) throws MusicDoesNotExistException {
        Music music = this.musicRepository.getMusicById(id);
        if(music ==null)
        {
            throw new MusicDoesNotExistException("Music not found with this id does not exist");
        }
        return MusicMapper.toDto(music);
    }

    public MusicResponseDTO addMusic(AddMusicDTO addMusicDTO)
    {

        Music music= MusicMapper.toDomain(addMusicDTO);
        Object principal= authenticationFacade.getAuthentication().getPrincipal();
        if(principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            User user = userRepository.getUserByUsername(username);
            if (user == null) {
                return null;
            }
            music.setUser(user);
            musicRepository.save(music);
            return MusicMapper.toDto(music);
        }
        return null;
    }


}
