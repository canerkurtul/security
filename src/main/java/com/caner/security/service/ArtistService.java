package com.caner.security.service;

import com.caner.security.repository.ArtistRepo;
import com.caner.security.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Ctrl + Shift + A
 * Alt + Enter  (gri boyayınca)
 * Ctrl + O             -- Override Implement methods
 * Ctrl + Shift + V     -- paste multiple choice
 * Ctrl + D             -- duplicate lines
 * Ctrl + Backspace     -- Delete lines
 * Ctrl + Alt + SHift + U   -- pom.xml dependency analyzer
 *
 * Multi line comment
 * Case sensitive çalışıyor
 */

@Service
public class ArtistService {

    private final ArtistRepo artistRepo;
    private final ArtistServiceAsync myAsync;

    public ArtistService(ArtistRepo artistRepo, ArtistServiceAsync myAsync) {
        this.artistRepo = artistRepo;
        this.myAsync = myAsync;
    }


    public List<Artist> queryByName(String name) {
        List<Artist> lst = artistRepo.queryArtistsByNameLike("%" +name+ "%");
        myAsync.countAndPrintLog();
        return lst;
    }

    public Long create2Artists() {
        long cnt = 0;
        try {
            cnt = createNextArtist(true);
        } catch (Exception ex) {
            System.out.println("EXCEPTION CAUGHT1: " + ex.getMessage());
        }
        cnt = createNextArtist(false);

        return cnt;
    }

    public Long createNextArtist(boolean throwException) {
        Artist a  = new Artist("Sanatçı " + System.currentTimeMillis(), "TR");
        artistRepo.save(a);
        if (throwException) {
            throw new RuntimeException("RUNTIME EXCEPTION THROWN");
        }
        return artistRepo.count();
    }

    public void deleteArtist(Long id) {
        artistRepo.deleteById(id);
    }
}
