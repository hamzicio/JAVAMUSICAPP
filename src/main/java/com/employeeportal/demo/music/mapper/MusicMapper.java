package com.employeeportal.demo.music.mapper;

import com.employeeportal.demo.music.dto.AddMusicDTO;
import com.employeeportal.demo.music.dto.MusicResponseDTO;
import com.employeeportal.demo.music.entity.Music;


public class MusicMapper {

    public static MusicResponseDTO toDto(Music music)
    {
        MusicResponseDTO musicResponseDTO =new MusicResponseDTO();
        musicResponseDTO.setName(music.getName());
        musicResponseDTO.setUrl(music.getUrl());
        musicResponseDTO.setImage(music.getImage());

        return musicResponseDTO;
    }

    public static Music toDomain(AddMusicDTO addMusicDTO)
    {
       Music music=new Music();
       music.setName(addMusicDTO.getName());
       music.setUrl(addMusicDTO.getUrl());
       music.setImage(addMusicDTO.getImage());
        return music;
    }

}