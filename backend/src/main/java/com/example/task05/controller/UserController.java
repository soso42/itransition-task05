package com.example.task05.controller;

import com.example.task05.model.UserModel;
import com.example.task05.model.UsersListModel;
import com.example.task05.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin(origins = "*")
public class UserController {

    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public ResponseEntity<UsersListModel> getAllUsers() {
        UsersListModel usersListModel = new UsersListModel();
        usersListModel.setUsers(userService.getAllUserNames());
        return new ResponseEntity<>(usersListModel, HttpStatus.OK);
    }

    @PostMapping("/user")
    public ResponseEntity<String> saveUser(@RequestBody UserModel userModel) {
        userService.addUserIfNotExists(userModel.getName());
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
