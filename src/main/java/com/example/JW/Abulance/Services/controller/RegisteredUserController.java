package com.example.JW.Abulance.Services.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.JW.Abulance.Services.entity.RegisteredUser;
import com.example.JW.Abulance.Services.service.implementations.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/users")
public class RegisteredUserController {
    private final UserServiceImpl user_service;

    public RegisteredUserController(UserServiceImpl user_service) {
        this.user_service = user_service;
    }

    // GET all users
    @GetMapping("/")
    public ResponseEntity<List<RegisteredUser>> getAllUsers() {
        List<RegisteredUser> all_users = user_service.getAllRegisteredUsers();

        return new ResponseEntity<>(all_users, HttpStatus.OK);
    }

    // POST a user
    @PostMapping("/")
    public ResponseEntity<RegisteredUser> postNewUser(@RequestBody RegisteredUser new_user) {
        RegisteredUser added_user = user_service.postUser(new_user);

        return new ResponseEntity<RegisteredUser>(added_user, HttpStatus.CREATED);
    }

    // PUT a user
    @PutMapping("/{id}")
    public ResponseEntity<RegisteredUser> editUser(
            @PathVariable("id") int user_id,
            RegisteredUser user_edit) {
        return new ResponseEntity<>(
                user_service.putRegisteredUser(user_id, user_edit),
                HttpStatus.OK);
    }

    // DELETE a user
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") int user_id) {
        user_service.deleteRegisteredUser(user_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
