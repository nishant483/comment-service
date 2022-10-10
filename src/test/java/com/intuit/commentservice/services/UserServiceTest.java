package com.intuit.commentservice.services;

import com.intuit.commentservice.models.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void createUser() {

        User user = userService.createUser("nishant@xeno.in","Nishant");
        assert user.getId() > 0;

    }

    @Test
    void findByEmail() {

        User user = userService.findByEmail("nishant@xeno.in");
        assert user.getId() > 0;
    }
}