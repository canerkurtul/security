package com.caner.security.repository;

import com.caner.security.model.Artist;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
@ExtendWith(MockitoExtension.class)
class ArtistRepoTest {

    @Autowired
    private ArtistRepo underTest;

//    @Autowired
//    ArtistRepoTest(ArtistRepo underTest) {
//        this.underTest = underTest;
//    }

    @BeforeEach
    void setUp() {
        Artist a = new Artist("Test Name","CA");
        underTest.save(a);
    }

    @AfterEach
    void tearDown() {
        underTest.deleteAll();
    }

    @Test
    void findByName_Existing() {
        List<Artist> aList = underTest.findByName("Test Name");

        assertThat(!aList.isEmpty()).isTrue();
    }

    @Test
    void findByName_NonExisting() {
        List<Artist> aList = underTest.findByName("Non Existing Person");

        assertThat(!aList.isEmpty()).isFalse();
    }


    @ParameterizedTest
    @CsvSource({
            "Test Name, true",
            "Non Existing Person, false"
    })
    void findByName_AllScenarios(String name, boolean expected) {
        List<Artist> aList = underTest.findByName(name);

        assertThat(!aList.isEmpty()).isEqualTo(expected);
    }

    @Test
    void queryArtistsByNameLike() {
    }
}