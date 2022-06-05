package com.example.task05.service;

import com.example.task05.entity.User;
import com.example.task05.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User saveUser(String name);
    void addUserIfNotExists(String name);
    List<String> getAllUserNames();
}
