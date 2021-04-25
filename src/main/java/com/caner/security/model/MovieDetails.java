package com.caner.security.model;

import com.caner.security.enums.Genre;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@Validated
@Entity
public class MovieDetails implements Serializable {

    @Id
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Movie movie;

    private Integer imdbRanking;

    private LocalDate releaseDate;

}
