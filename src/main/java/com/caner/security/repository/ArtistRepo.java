package com.caner.security.repository;


import com.caner.security.model.Artist;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistRepo extends CrudRepository<Artist, Long> {

    // Artist findById(long id);

    List<Artist> findByName(String name);

    List<Artist> queryArtistsByNameLike(String name);

}
