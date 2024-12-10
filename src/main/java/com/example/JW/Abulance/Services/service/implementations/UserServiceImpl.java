package com.example.JW.Abulance.Services.service.implementations;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.JW.Abulance.Services.entity.RegisteredUser;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.repository.UserRepo;
import com.example.JW.Abulance.Services.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepo user_repo;

    public UserServiceImpl(UserRepo user_repo) {
        this.user_repo = user_repo;
    }

    @Override
    public List<RegisteredUser> getAllRegisteredUsers() {
        return user_repo.findAll();
    }

    @Override
    public RegisteredUser postUser(RegisteredUser user) {
        return user_repo.save(user);
    }

    @Override
    public RegisteredUser putRegisteredUser(int user_id, RegisteredUser user) {
        if (!user_repo.existsById(user.getId()))
            throw new EntityNotExistingException(
                    "User with id " + user_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName());

        RegisteredUser edit = new RegisteredUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
                user.getEmail(),
                user.getUserrole(),
                user.getUsertype());

        return user_repo.save(edit);
    }

    @Override
    public RegisteredUser getRegisteredUser(int user_id) {
        return user_repo.findById(user_id).get();
    }

    @Override
    public void deleteRegisteredUser(int user_id) {
        RegisteredUser user = user_repo
                .findById(user_id)
                .orElseThrow(() -> new EntityNotExistingException(
                        "User with id " + user_id + "doesnt exist\n Associated Entity:"
                                + this.getClass().getSimpleName()));

        user_repo.delete(user);
    }
}
