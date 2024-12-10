package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.RegisteredUser;

public interface UserService {
    List<RegisteredUser> getAllRegisteredUsers();
    
    RegisteredUser getRegisteredUser(int user_id);

    RegisteredUser postUser(RegisteredUser user);

    RegisteredUser putRegisteredUser(int user_id, RegisteredUser user);

    void deleteRegisteredUser(int user_id);
}
