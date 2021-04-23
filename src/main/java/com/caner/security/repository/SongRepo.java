package com.caner.security.repository;

import com.caner.security.model.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepo extends JpaRepository<Song, Long> {

    // List<Song> findBy
}
