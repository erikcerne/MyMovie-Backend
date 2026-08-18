package com.example.MyMovies.user;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    UserRepository jpa;

    public UserService(UserRepository jpa) {
        this.jpa = jpa;
    }

    public boolean isExistingUser(String id) {
        return jpa.existsById(id);
    }

    public void registerUser(String id, String name) {
        User user = new User();
        user.setUserId(id);
        user.setUsername(name);
        jpa.save(user);
    }
}
