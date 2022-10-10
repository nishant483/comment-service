package com.intuit.commentservice.services;

import com.intuit.commentservice.models.User;
import com.intuit.commentservice.repository.UserRepoInterface;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepoInterface userRepoInterface;

    public UserService(UserRepoInterface userRepoInterface) {
        this.userRepoInterface = userRepoInterface;
    }

    public User createUser(String email, String name){

        if(userRepoInterface.existsByEmail(email))
                 return userRepoInterface.findByEmail(email);
        return userRepoInterface.save(new User(name,email));
    }

    public User findByEmail(String email){

        if(userRepoInterface.existsByEmail(email))
            return userRepoInterface.findByEmail(email);
        else
            return null;
    }
}
