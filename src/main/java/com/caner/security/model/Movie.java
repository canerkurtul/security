package com.caner.security.model;

import com.caner.security.enums.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Validated
@Entity
@Table(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    // @Basic(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Artist mainArtist;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "MOVIE_ALL_ARTISTS",
            joinColumns = {@JoinColumn(name = "MOVIE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "ARTIST_ID")}
    )
    @Fetch(FetchMode.JOIN)
    private List<Artist> allArtists;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @JoinColumn
    private Long boxid;

}
