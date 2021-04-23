package com.caner.security.service;

import com.caner.security.repository.ArtistRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class ArtistServiceAsync {
    @Autowired private ArtistRepo artistRepo;

    @Async
    public void countAndPrintLog() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Long cnt = artistRepo.count();
        System.out.println("ARTIST COUNT IS: " + cnt);
    }

    @PostConstruct
    public void postConstructActions() {
        System.out.println("Execute at PostConstruct");
    }
}
