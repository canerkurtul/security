package com.caner.security.redis;

import com.caner.security.models.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class RedisClient {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    public void write(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void read(String key) {
        String value = redisTemplate.opsForValue().get(key);
        System.out.println("REDIS [" +key+ ", " +value+ "]");
    }

    public void writeArtist(Artist a) {
        objectRedisTemplate.opsForValue().set(a.getName(), a);

        objectRedisTemplate.convertAndSend("REDIS-MSG-CHANNEL", a);
    }
    public void readArtist(String key) {
        Object a = objectRedisTemplate.opsForValue().get(key);
        System.out.println("REDIS ARTIST [" +key+ ", " +a+ "]");
    }


}
