package com.sha.redis.controller;

import com.sha.redis.domain.User;
import com.sha.redis.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

@RestController
public class RedisController {

    private final Logger LOG = LoggerFactory.getLogger(getClass());


    final UserRepository userRepository;

    public RedisController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @GetMapping("/")
    public String index(){
        return "<h2>Hello Redis</h2>";
    }

    @Cacheable(value = "users", key = "#userId", unless = "#result.followers < 12000")
    @RequestMapping(value = "/{userId}", method = RequestMethod.GET)
    public User getUser(@PathVariable String userId) {
        LOG.info("Getting user with ID {}.", userId);
        return userRepository.findById(Long.valueOf(userId)).get();
    }

    @CachePut(value = "users", key = "#user.id")
    @PutMapping("/update")
    public User updatePersonByID(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @CacheEvict(value = "users", allEntries=true)
    @DeleteMapping("/{id}")
    public void deleteUserByID(@PathVariable Long id) {
        LOG.info("deleting person with id {}", id);
        userRepository.deleteById(id);
    }

}
