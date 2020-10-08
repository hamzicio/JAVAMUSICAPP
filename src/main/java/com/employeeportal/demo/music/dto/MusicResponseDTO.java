package com.employeeportal.demo.music.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MusicResponseDTO extends RepresentationModel<MusicResponseDTO> {
    private String name;
}
