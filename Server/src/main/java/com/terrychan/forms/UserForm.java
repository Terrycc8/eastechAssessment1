package com.terrychan.forms;

import com.terrychan.models.User;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.lang.NonNull;


public class UserForm {
    @NotBlank
    private String first_name;
    @NotBlank
    private String last_name;

    public void setFirst_name(String firstName) {
        this.first_name = firstName;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public User toUser(){
        System.out.println("1:"+this.first_name);
        System.out.println("2:"+this.last_name);
        var user = new User(this.first_name,this.last_name);
        return user;
    }
}
