package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Request;

public interface RequestService {
    List<Request> getAllRequests();

    Request postNewRequest(Request new_request);

    Request putExistingRequest(int request_id, Request request_to_edit);

    void deleteRequest(int request_id);

}
