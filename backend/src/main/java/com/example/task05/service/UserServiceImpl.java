package com.example.task05.service;

import com.example.task05.entity.User;
import com.example.task05.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User saveUser(String name) {
        User user = User.builder()
                .name(name)
                .build();
        return userRepository.save(user);
    }

    @Override
    public void addUserIfNotExists(String name) {
        Optional<User> optUser = userRepository.findUserByName(name);
        if (optUser.isEmpty()) {
            saveUser(name);
        }
    }

    @Override
    public List<String> getAllUserNames() {
        return userRepository.findAll().stream()
                .map(User::getName)
                .collect(Collectors.toList());
    }


}
