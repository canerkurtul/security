package com.caner.security.controller;

import com.caner.security.model.Artist;
import com.caner.security.repository.ArtistRepo;
import com.caner.security.service.ArtistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/artists")
public class ArtistController {

    private final ArtistRepo artistRepo;
    private final ArtistService artistService;

    @Autowired
    public ArtistController(ArtistRepo artistRepo, ArtistService artistService) {
        this.artistRepo = artistRepo;
        this.artistService = artistService;
    }

    @GetMapping(path = "/count" )
    public String getCount() {
        return "" + artistRepo.count();
    }

    @GetMapping(path = "/{id}" )
    public ResponseEntity<Artist> getById(@PathVariable(required = true) Long id) {
        Optional<Artist> artist = artistRepo.findById(id);
        return ResponseEntity.ok(artist.orElse(null));
    }

    @GetMapping(path = "/find/{id}" )
    public Artist getById2(@PathVariable(required = true) Long id) {
        Optional<Artist> artist = artistRepo.findById(id);
        return artist.orElse(null);
    }

    @GetMapping(path = "/put" )
    public Long put() {
        return artistService.createNextArtist(false);
    }

    @GetMapping(path = "/put2" )
    public Long put2() {    // Transaction marked as rollback only test
        return artistService.create2Artists();
    }

    @GetMapping(path = "/queryByName/{name}" )
    public List<Artist> queryByName(@PathVariable(required = true) String name) {
        return artistService.queryByName(name);
    }

    @DeleteMapping(path = "/{id}")
    public void deleteById(@PathVariable(required = true) Long id) {
        artistService.deleteArtist(id);
    }

}
