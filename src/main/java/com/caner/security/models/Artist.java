package com.caner.security.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "artist")
public class Artist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NonNull
    private String name;

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

}
