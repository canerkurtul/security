package com.caner.security.redis;

import com.caner.security.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
//@ConditionalOnProperty(
//        value = "spring.profiles.active",
//        havingValue = "prod",
//        matchIfMissing = true)
@ConditionalOnExpression("'${spring.profiles.active}'!='local'")
public class RedisClient {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Autowired
    private RedisTemplate<String, Object> objectRedisTemplate;

    private static final long CACHE_TTL_SECONDS = 10;

    public void write(String key, String value) {
        redisTemplate.opsForValue().set(key, value, Duration.ofSeconds(CACHE_TTL_SECONDS));
    }

    public void read(String key) {
        String value = redisTemplate.opsForValue().get(key);
        System.out.println("REDIS [" +key+ ", " +value+ "]");
    }

    public void writeArtist(Artist a) {
        objectRedisTemplate.opsForValue().set(a.getName(), a, Duration.ofSeconds(CACHE_TTL_SECONDS));

        objectRedisTemplate.convertAndSend("REDIS-MSG-CHANNEL", a);
    }
    public void readArtist(String key) {
        Object a = objectRedisTemplate.opsForValue().get(key);
        System.out.println("REDIS ARTIST [" +key+ ", " +a+ "]");
    }


}
