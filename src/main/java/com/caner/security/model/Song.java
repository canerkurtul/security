package com.caner.security.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Validated
@Entity
@Table(name = "song")
public class Song {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String title;

    // @Basic(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.LAZY)
    private Artist artist;


}
