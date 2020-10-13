package com.employeeportal.demo.music.service;

import com.employeeportal.demo.music.dto.AddMusicDTO;
import com.employeeportal.demo.music.dto.MusicResponseDTO;
import com.employeeportal.demo.music.dto.MusicWithPagingDTO;
import com.employeeportal.demo.music.exception.AccessDeniedException;
import com.employeeportal.demo.music.exception.MusicDoesNotExistException;
import com.employeeportal.demo.music.mapper.MusicMapper;
import com.employeeportal.demo.music.repository.MusicRepository;
import com.employeeportal.demo.music.entity.Music;
import com.employeeportal.demo.user.config.IAuthenticationFacade;
import com.employeeportal.demo.user.entity.User;
import com.employeeportal.demo.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import  org.springframework.data.domain.Pageable;
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


    public MusicWithPagingDTO getAllMusic(Integer pageNo, Integer pageSize)
    {
        Pageable firstPageWithTwoElements = PageRequest.of(pageNo, pageSize);
        Page<Music> musicList =musicRepository.findAll(firstPageWithTwoElements);
        MusicWithPagingDTO musicWithPagingDTO=new MusicWithPagingDTO();

        if(musicList.hasContent()) {
            musicWithPagingDTO.setMusic(musicList.getContent());
            musicWithPagingDTO.setTotalTracks(musicList.getNumberOfElements());
            musicWithPagingDTO.setPages(musicList.getTotalPages());

            return  musicWithPagingDTO;
        }
        return musicWithPagingDTO;
    }

    public MusicWithPagingDTO deleteMusicById(Integer id) throws AccessDeniedException, MusicDoesNotExistException {
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
                MusicWithPagingDTO musicWithPagingDTO= getMusicByUsername(0,10);
                return musicWithPagingDTO;
            }
            else
            {
                throw new MusicDoesNotExistException("Music not found with this id does not exist");
            }

        }
        return null;
    }

    public MusicWithPagingDTO getMusicByUsername(Integer pageNo, Integer pageSize) throws MusicDoesNotExistException {
        Object principal= authenticationFacade.getAuthentication().getPrincipal();
        String username = ((UserDetails) principal).getUsername();

        Pageable firstPageWithTwoElements= PageRequest.of(pageNo, pageSize);
        Page<Music> musicList =musicRepository.findAllByUserName(username,firstPageWithTwoElements);
        if(musicList ==null)
        {
            throw new MusicDoesNotExistException("Music found with this username does not exist");
        }
        MusicWithPagingDTO musicWithPagingDTO= new MusicWithPagingDTO();
        musicWithPagingDTO.setMusic(musicList.getContent());
        musicWithPagingDTO.setTotalTracks((int) musicList.getTotalElements());
        musicWithPagingDTO.setPages(musicList.getTotalPages());

        return musicWithPagingDTO;
    }

    public MusicWithPagingDTO addMusic(AddMusicDTO addMusicDTO) throws MusicDoesNotExistException {

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

            MusicWithPagingDTO musicWithPagingDTO= getMusicByUsername(0,10);

            return musicWithPagingDTO;
        }
        return null;
    }


}
