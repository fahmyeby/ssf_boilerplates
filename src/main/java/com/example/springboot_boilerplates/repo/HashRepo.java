package com.example.springboot_boilerplates.repo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class HashRepo {
    @Autowired
    @Qualifier("redisTemplate")
    RedisTemplate<String, String> template;
    
    // day 15 slide 36 (save to redis)
    public void create(String redisKey, String hashKey, String hashValue){
        template.opsForHash().put(redisKey, hashKey, hashValue);
    }

    // slide 37 (get by id)
    public Object get(String redisKey, String hashKey, String hashValue){
        return template.opsForHash().get(redisKey, hashKey);
    }

    //slide 38 (delete)
    public Long delete(String redisKey, String hashKey, String hashValue){
       return template.opsForHash().delete(redisKey, hashKey);
    }

    //slide 39 - 
    public Boolean keyExists(String redisKey, String hashKey){
        return template.opsForHash().hasKey(redisKey, hashKey);
    }

    // slide 40 - <Object, Object> = <hashKey, hashValue> (get all)
    public Map<Object, Object> getEntries(String redisKey){
        return template.opsForHash().entries(redisKey);
    }

    // slide 40 - as per slide
    public Set<Object> getKey(String redisKey){
        return template.opsForHash().keys(redisKey);
    }
    public List<Object> getValues(String redisKey){
        return template.opsForHash().values(redisKey);
    }

    // slide 41
    public Long size(String redisKey){
        return template.opsForHash().size(redisKey);
    }

    public void expire(String redisKey, Integer expireValue){
        Duration expireDuration = Duration.ofSeconds(expireValue);
        template.expire(redisKey, expireDuration);
    }


    ////////////////////////////////////////////////////

    // Add or update a single key-v/* */alue pair in a hash
    public void setMap(String folder, String key, String value) {
        template.opsForHash().put(folder, key, value);
    }

    // Add multiple key-value pairs to a hash
    public void setMapAll(String folder, HashMap<String, String> map) {
        template.opsForHash().putAll(folder, map);
    }

    // Retrieve a value for a specific key in a hash
    public String getValueFromMap(String folder, String key) {
        return (String) template.opsForHash().get(folder, key);
    }

    // Retrieve all key-value pairs in a hash
    public Map<Object, Object> getAllFromMap(String folder) {
        return template.opsForHash().entries(folder);
    }

    // Check if a key exists in a hash
    public boolean hasKey(String folder, String key) {
        return template.opsForHash().hasKey(folder, key);
    }

    // Delete a specific key from a hash
    public void deleteKeyFromMap(String folder, String key) {
        template.opsForHash().delete(folder, key);
    }

    // Retrieve all keys in a hash
    public Set<Object> getAllKeys(String folder) {
        return template.opsForHash().keys(folder);
    }

    // Retrieve all values in a hash
    public Set<Object> getAllValues(String folder) {
        return (Set<Object>) template.opsForHash().values(folder);
    }

    // Increment a numeric value in a hash
    public void incrementHashValue(String folder, String key, long delta) {
        template.opsForHash().increment(folder, key, delta);
    }

    // Increment a floating-point value in a hash
    public void incrementHashValue(String folder, String key, double delta) {
        template.opsForHash().increment(folder, key, delta);
    }

    // Get the size of a hash (number of keys)
    public long getHashSize(String folder) {
        return template.opsForHash().size(folder);
    }

    // Check if a hash exists
    public boolean hashExists(String folder) {
        return template.hasKey(folder);
    }
}
