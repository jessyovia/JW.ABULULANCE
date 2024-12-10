package com.example.JW.Abulance.Services.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.JW.Abulance.Services.entity.Request;

public interface RequestRepo extends JpaRepository<Request, Integer> {
}
