package com.caner.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class MovieBox {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    private Integer year;

    private Long artistId;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Cascade({ org.hibernate.annotations.CascadeType.ALL })
    @OrderBy("title")
    @Fetch(FetchMode.SUBSELECT)
    @JoinTable(
            name = "MOVIEBOX_MOVIES",
            joinColumns = {@JoinColumn(name = "MOVIEBOX_ID", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "MOVIE_ID")}
    )
    private List<Movie> movies;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "boxid")
    private List<Movie> moviesDirect;
}
