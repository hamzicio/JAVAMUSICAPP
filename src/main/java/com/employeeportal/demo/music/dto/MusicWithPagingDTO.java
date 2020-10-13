package com.employeeportal.demo.music.dto;

import com.employeeportal.demo.music.entity.Music;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class MusicWithPagingDTO {
    private List<Music> music;
    private Integer totalTracks;
    private Integer pages;
}
