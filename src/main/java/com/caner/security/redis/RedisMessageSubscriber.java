package com.caner.security.redis;

import com.caner.security.model.Artist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.stereotype.Service;

@Service
//@ConditionalOnProperty(
//        value = "spring.profiles.active",
//        havingValue = "prod",
//        matchIfMissing = true)
@ConditionalOnExpression("'${spring.profiles.active}'!='local'")
public class RedisMessageSubscriber implements MessageListener {

    @Autowired
    @Qualifier(value="objectRedisTemplate")
    private RedisTemplate<String, Object> objectRedisTemplate;

    @Override
    public void onMessage(Message message, byte[] bytes) {
        // Artist a = (Artist) objectRedisTemplate.getValueSerializer().deserialize(message.getBody());
        Artist a = (Artist) (new GenericJackson2JsonRedisSerializer()).deserialize(message.getBody());

        System.out.println("+++ REDIS Message received: " + message.toString());
        System.out.println("+++ REDIS Message received: " + a);
    }
}
