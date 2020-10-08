package com.employeeportal.demo.music.repository;

import com.employeeportal.demo.music.entity.Music;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MusicRepository extends JpaRepository<Music, Integer> {
    Music getMusicById(Integer id);
}
