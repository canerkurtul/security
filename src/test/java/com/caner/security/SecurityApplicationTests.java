package com.caner.security;

import com.caner.security.repository.ArtistRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class SecurityApplicationTests {

    @Autowired
    private SecurityApplication securityApplication;


    @Test
    void contextLoads() {
        assertThat(securityApplication).isNotNull();
    }

}
