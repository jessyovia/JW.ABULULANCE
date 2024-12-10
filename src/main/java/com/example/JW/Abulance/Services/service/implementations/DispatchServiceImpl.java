package com.example.JW.Abulance.Services.service.implementations;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Dispatch;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.repository.DispatchRepo;
import com.example.JW.Abulance.Services.service.DispatchService;

public class DispatchServiceImpl implements DispatchService {
    private final DispatchRepo dispatch_repo;

    public DispatchServiceImpl(DispatchRepo dispatch_repo) {
        this.dispatch_repo = dispatch_repo;
    }

    @Override
    public Dispatch putExistingDispatch(int dispatch_id, Dispatch dispatch_to_edit) {
        // ensures dispatch ID exists
        if (!dispatch_repo.existsById(dispatch_id))
            throw new EntityNotExistingException("History with id " + dispatch_id + "doesnt exist\n Associated Entity:"
                    + this.getClass().getSimpleName());

        Dispatch edit = new Dispatch();

        edit.setAmbulances(dispatch_to_edit.getAmbulances());
        edit.setRequest(dispatch_to_edit.getRequest());
        edit.setResolve_time(dispatch_to_edit.getResolve_time());
        edit.setEmployee(dispatch_to_edit.getEmployee());

        return dispatch_repo.save(edit);
    }

    @Override
    public Dispatch postNewDispatch(Dispatch new_dispatch) {
        return dispatch_repo.save(new_dispatch);
    }

    @Override
    public List<Dispatch> getAllDispatches() {
        return dispatch_repo.findAll();
    }

    @Override
    public Dispatch getDispatch(int dispatch_id) {
        return dispatch_repo.findById(dispatch_id).get();
    }

    @Override
    public void deleteDispatch(int dispatch_id) {
        dispatch_repo.deleteById(dispatch_id);
    }
}
