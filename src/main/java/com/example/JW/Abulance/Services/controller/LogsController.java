package com.example.JW.Abulance.Services.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.JW.Abulance.Services.entity.Logs;
import com.example.JW.Abulance.Services.service.implementations.LogsServiceImpl;

@RestController
@RequestMapping("/api/v1/logs")
public class LogsController {
    private final LogsServiceImpl logs_service;

    public LogsController(LogsServiceImpl logs_service) {
        this.logs_service = logs_service;
    }

    // GET all logs members
    @GetMapping("/")
    public ResponseEntity<List<Logs>> getAllLogs() {
        List<Logs> all_staffs = logs_service.getAllLogs();

        return new ResponseEntity<>(all_staffs, HttpStatus.OK);
    }

    // GET all logs in a paginated format
    @GetMapping("/paginated/")
    public ResponseEntity<Page<Logs>> getAllLogsPaginated(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Logs> all_logs_paginated = logs_service.getAllLogsPaginated(page, size);

        return new ResponseEntity<>(all_logs_paginated, HttpStatus.OK);
    }

    // POST a log
    @PostMapping("/")
    public ResponseEntity<Logs> postNewLog(@RequestBody Logs new_log) {
        Logs added_log = logs_service.postNewLog(new_log);

        return new ResponseEntity<Logs>(added_log, HttpStatus.CREATED);
    }

    // PUT a log
    @PutMapping("/{id}")
    public ResponseEntity<Logs> editLogs(
            @PathVariable("id") int log_id,
            Logs log_edit) {
        return new ResponseEntity<>(
                logs_service.putExistingLog(log_id, log_edit),
                HttpStatus.OK);
    }

    // DELETE a log
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteLog(@PathVariable("id") int log_id) {
        logs_service.deleteLog(log_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
