package com.caner.security.cache;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

import java.time.Duration;

@Configuration
@EnableCaching
@EnableRedisRepositories()
public class CacheableConfig {

    @Bean(name = "ConcurrentMapCacheManager")
    @Primary
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager("cache-1", "cache-2");
    }

    @Bean(name = "RedisCacheManager")
    public RedisCacheManager cacheManagerRedis(JedisConnectionFactory jedisConnectionFactory) {
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofSeconds(6))
                .disableCachingNullValues();

        RedisCacheManager rcm = RedisCacheManager
                .builder(jedisConnectionFactory)
                .cacheDefaults(config)
                // .transactionAware()
                .build();

        return rcm;
    }

}
