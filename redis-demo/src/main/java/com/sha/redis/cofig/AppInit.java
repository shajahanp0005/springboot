package com.sha.redis.cofig;

import com.sha.redis.domain.User;
import com.sha.redis.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppInit implements CommandLineRunner {

    final UserRepository userRepository;

    public AppInit(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        userRepository.save(new User("AAA",1000));
        userRepository.save(new User("BBB",12000));
        userRepository.save(new User("CCC",4000));
        userRepository.save(new User("DDD",13000));

    }
}
