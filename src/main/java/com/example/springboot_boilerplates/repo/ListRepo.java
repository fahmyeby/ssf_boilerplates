package com.example.springboot_boilerplates.repo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ListRepo {
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate<String, String> template;

    // slide 30, slide 34
    public void leftPush(String key, String value) {
        template.opsForList().leftPush(key, value);
    }
    
    public void rightPush(String key, String value) {
        template.opsForList().rightPush(key, value);
    }

    // slide 30
    public void leftPop(String key) {
        template.opsForList().leftPop(key, 1);
    }

    // slide 32
    public String get(String key, Integer index) {
        return template.opsForList().index(key, index).toString();
    }

    // slide 33
    public Long size(String key) {
        return template.opsForList().size(key);
    }

    public List<String> getList(String key) {
        List<String> list = template.opsForList().range(key, 0, -1);

        return list;
    }
}
