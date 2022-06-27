package com.example.exam.repository;

import com.example.exam.models.entity.Song;
import com.example.exam.models.entity.StyleEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SongRepository extends JpaRepository<Song, Long> {
        Optional<Song> findByPerformer(String performer);
        List<Song>findAllByStyle_Name(StyleEnum name);
}
