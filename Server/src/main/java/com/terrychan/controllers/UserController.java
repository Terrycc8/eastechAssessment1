package com.terrychan.controllers;

import com.terrychan.forms.UserForm;
import com.terrychan.models.User;
import com.terrychan.repositories.UserRepositories;
import com.terrychan.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "", method = RequestMethod.POST)
    public int setUser(@Valid @RequestBody UserForm userform){
        return userService.setUser(userform.toUser());
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUserById(@PathVariable int id){
        return userService.getUserById(id);
    }


}
