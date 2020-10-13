package com.employeeportal.demo.music.repository;

import com.employeeportal.demo.music.entity.Music;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import  org.springframework.data.domain.Pageable;



@Repository
public interface MusicRepository extends PagingAndSortingRepository<Music, Integer> {
    Music getMusicById(Integer id);

    Page<Music> findAllByUserName(String username,Pageable pageable);
}
