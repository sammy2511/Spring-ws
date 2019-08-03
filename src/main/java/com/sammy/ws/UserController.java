package com.sammy.ws;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sammy.ws.ui.model.request.UserBean;
import com.sammy.ws.ui.model.response.UserResponse;

@RestController
@RequestMapping("users") // https://localhost:8080/users base url
public class UserController {

    @GetMapping
    public String getUser(){
        return "Hello!! From rest Get Method";
    }
    
    @PostMapping
    public UserResponse addUser(@RequestBody UserBean userBean) {
    	return new UserResponse();
    }
    
    @DeleteMapping
    public String deleteUser() {
    	return "Hello!! From Rest Delete Method";
    }
    
    @PutMapping
    public String updateUser() {
    	return "Hello!! From Rest Update Method";
    }
}
