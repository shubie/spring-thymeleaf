package com.codekraft.cdfipb.recruitementfrontend.repositories;

import com.codekraft.cdfipb.recruitementfrontend.domains.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findUserByUsername(String username);
//    Optional<User> findUserByUsername(String username);
}
