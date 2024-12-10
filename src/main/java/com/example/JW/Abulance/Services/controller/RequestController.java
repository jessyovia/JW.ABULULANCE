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

import com.example.JW.Abulance.Services.entity.Request;
import com.example.JW.Abulance.Services.service.implementations.RequestServiceImpl;

@RestController
@RequestMapping("/api/v1/requests")
public class RequestController {
    private final RequestServiceImpl request_service;

    public RequestController(RequestServiceImpl request_service) {
        this.request_service = request_service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Request>> getAllRequests() {
        List<Request> all_requestes = request_service.getAllRequests();

        return new ResponseEntity<List<Request>>(all_requestes, HttpStatus.OK);
    }

    // POST a request
    @PostMapping("/")
    public ResponseEntity<Request> postNewRequest(@RequestBody Request new_record) {
        Request added_record = request_service.postNewRequest(new_record);

        return new ResponseEntity<Request>(added_record, HttpStatus.CREATED);
    }

    // PUT a request
    @PutMapping("/{id}")
    public ResponseEntity<Request> editRequest(
            @PathVariable("id") int request_id,
            Request request_edit) {
        return new ResponseEntity<Request>(
                request_service.putExistingRequest(request_id, request_edit),
                HttpStatus.OK);
    }

    // DELETE a request record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteRequest(@PathVariable("id") int request_id) {
        request_service.deleteRequest(request_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}