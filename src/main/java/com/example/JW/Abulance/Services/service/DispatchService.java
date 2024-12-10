package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Dispatch;

public interface DispatchService {
    List<Dispatch> getAllDispatches();

    Dispatch getDispatch(int dispatch_id);

    Dispatch postNewDispatch(Dispatch new_dispatch);

    Dispatch putExistingDispatch(int dispatch_id, Dispatch dispatch_to_edit);

    void deleteDispatch(int dispatch_id);
}
