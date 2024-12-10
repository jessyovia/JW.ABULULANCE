package com.example.JW.Abulance.Services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JW.Abulance.Services.entity.RegisteredUser;

public interface UserRepo extends JpaRepository<RegisteredUser, Integer> {
    
}
