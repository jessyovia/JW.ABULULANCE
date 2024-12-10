package com.example.JW.Abulance.Services.service.implementations;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Request;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.repository.RequestRepo;
import com.example.JW.Abulance.Services.service.RequestService;

public class RequestServiceImpl implements RequestService {
    private final RequestRepo request_repo;

    public RequestServiceImpl(RequestRepo request_repo) {
        this.request_repo = request_repo;
    }

    @Override
    public List<Request> getAllRequests() {
        return request_repo.findAll();
    }

    @Override
    public void deleteRequest(int request_id) {
        Request request = request_repo
                .findById(request_id)
                .orElseThrow(() -> new EntityNotExistingException("Request with id " + request_id
                        + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName()));

        request_repo.delete(request);
    }

    @Override
    public Request postNewRequest(Request new_request) {
        return request_repo.save(new_request);
    }

    @Override
    public Request putExistingRequest(int request_id, Request request_to_edit) {
        // ensures patient ID exists
        if (!request_repo.existsById(request_id))
            throw new EntityNotExistingException("Request with id " + request_id + "doesnt exist\n Associated Entity:"
                    + this.getClass().getSimpleName());

        Request edit = new Request();

        edit.setCaller_name(request_to_edit.getCaller_name());
        edit.setCaller_phone(request_to_edit.getCaller_phone());
        edit.setIncident_location(request_to_edit.getIncident_location());
        edit.setIncident_type(request_to_edit.getIncident_type());
        edit.setSeverity(request_to_edit.getSeverity());
        edit.setStatus(request_to_edit.getStatus());

        return request_repo.save(edit);
    }
}
