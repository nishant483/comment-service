package com.intuit.commentservice.controllers;

import com.intuit.commentservice.models.User;
import com.intuit.commentservice.services.UserService;
import lombok.NonNull;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "/user",produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(value = "findByEmail/{email}")
    public @ResponseBody User getUser(@PathVariable String email) {
        return userService.findByEmail(email);
    }

    @GetMapping(value = "/createUser", produces = "application/json")
    public @ResponseBody User createUser(@RequestParam("email") @NonNull String email,@RequestParam("name") @NonNull String name) {
        return userService.createUser(email,name);
    }

}
