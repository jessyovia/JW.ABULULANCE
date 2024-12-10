package com.example.JW.Abulance.Services.service;

import java.util.List;

import com.example.JW.Abulance.Services.entity.Logs;

public interface LogsService {
    List<Logs> getAllLogs();

    Logs getLog(int log_id);

    Logs postNewLog(Logs log_to_add);

    Logs putExistingLog(int log_id, Logs log_to_edit);

    void deleteLog(int log_id);
}
