package com.terrychan.services;

import com.terrychan.models.User;
import com.terrychan.repositories.UserRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepositories userRepositories;
    public int setUser(User user){
        return userRepositories.save(user).getId();
    }
    public User getUserById(int id){

        if(userRepositories.existsById(id)){
            return userRepositories.findById(id);
        } else throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"User Id does not exist!");


    }
}
