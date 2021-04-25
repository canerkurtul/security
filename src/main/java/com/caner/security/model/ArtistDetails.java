package com.caner.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table
public class ArtistDetails implements Serializable {

    @Id
    @Column(name = "artist_id")
    private Long id;

    private String biography;

    @OneToOne
    @MapsId
    @JoinColumn(name = "artist_id")
    private Artist artist;

}
