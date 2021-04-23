package com.caner.security.service;

import com.caner.security.model.Artist;
import com.caner.security.repository.ArtistRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ArtistServiceTest {

    @Mock private ArtistRepo artistRepo;
    @Mock private ArtistServiceAsync artistServiceAsync;

    private ArtistService underTest;

    @BeforeEach
    void setUp() {
        underTest = new ArtistService(artistRepo, artistServiceAsync);
    }

    @Test
    void queryByName() {
        String nameToTest = "name to test";
        List<Artist> aList = underTest.queryByName(nameToTest);

        assertThat(aList.size()).isEqualTo(0);
        verify(artistRepo).queryArtistsByNameLike("%" +nameToTest+ "%");
        verify(artistServiceAsync).countAndPrintLog();
    }

    @Test
    void create2Artists() {
        underTest.create2Artists();

        // given(artistRepo.save(any())).willThrow(new RuntimeException("Error Occurred"));
        given(artistRepo.save(any())).willReturn(new Artist("Name Surname", "BE"));

        assertThatThrownBy(() -> underTest.createNextArtist(true))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("RUNTIME EXCEPTION THROWN");
        verify(artistRepo).count();

        verify(artistRepo, Mockito.atLeast(3)).save(any());
    }

}