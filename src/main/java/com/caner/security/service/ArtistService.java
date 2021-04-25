package com.caner.security.service;

import com.caner.security.repository.ArtistRepo;
import com.caner.security.model.Artist;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    private final ArtistRepo artistRepo;
    private final ArtistServiceAsync myAsync;

    public ArtistService(ArtistRepo artistRepo, ArtistServiceAsync myAsync) {
        this.artistRepo = artistRepo;
        this.myAsync = myAsync;
    }


    @Cacheable(cacheManager = "RedisCacheManager", cacheNames = "cache-24")
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
        Artist a  = new Artist("Artist " + System.currentTimeMillis(), "TR");
        artistRepo.save(a);
        if (throwException) {
            throw new RuntimeException("RUNTIME EXCEPTION THROWN");
        }
        return artistRepo.count();
    }

    public void deleteArtist(Long id) {
        artistRepo.deleteById(id);
    }

    public Long save(Artist a) {
        a  = artistRepo.save(a);
        return a.getId();
    }
}
