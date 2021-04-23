package com.caner.security.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "artist")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(updatable = false)
    private String country;

    public Artist(String name, String country) {
        this.name = name;
        this.country = country;
    }

//    public void setId(Long id) { this.id = id;  }
//    public void setName(String name) { this.name = name;  }
//    public void setCountry(String country) { this.country = country;  }

    @Override
    public String toString() {
        return "{ARTIST: " + name + "}";
    }

    @OneToMany(mappedBy = "artist")
    private Collection<Song> song;

    public Collection<Song> getSong() {
        return song;
    }

    public void setSong(Collection<Song> song) {
        this.song = song;
    }
}
