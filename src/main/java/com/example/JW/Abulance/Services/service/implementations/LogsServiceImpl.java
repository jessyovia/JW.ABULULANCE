package com.example.JW.Abulance.Services.service.implementations;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.JW.Abulance.Services.entity.Logs;
import com.example.JW.Abulance.Services.exceptions.EntityNotExistingException;
import com.example.JW.Abulance.Services.repository.LogsRepo;
import com.example.JW.Abulance.Services.service.LogsService;

@Service
public class LogsServiceImpl implements LogsService {
    private final LogsRepo logs_repo;

    public LogsServiceImpl(LogsRepo logs_repo) {
        this.logs_repo = logs_repo;
    }

    @Override
    public List<Logs> getAllLogs() {
        return logs_repo.findAll();
    }

    public Page<Logs> getAllLogsPaginated(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        return logs_repo.findAll(pageable);
    }

    @Override
    public Logs getLog(int log_id) {
        if (!logs_repo.existsById(log_id))
            throw new EntityNotExistingException(
                    "Logs with id " + log_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName());

        return logs_repo.findById(log_id).get();
    }

    @Override
    public Logs postNewLog(Logs new_log) {
        return logs_repo.save(new_log);
    }

    @Override
    public Logs putExistingLog(int log_id, Logs log_to_edit) {
        // ensures logs ID exists
        if (!logs_repo.existsById(log_id))
            throw new EntityNotExistingException(
                    "Logs with id " + log_id + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName());

        Logs edit = new Logs();

        edit.setOutcome(log_to_edit.getOutcome());
        edit.setPatient(log_to_edit.getPatient());
        edit.setDispatch(log_to_edit.getDispatch());
        edit.setUpdated_at(LocalDateTime.now());

        return logs_repo.save(edit);
    }

    @Override
    public void deleteLog(int log_id) {
        Logs searched_logs = logs_repo
                .findById(log_id)
                .orElseThrow(() -> new EntityNotExistingException("logs with id " + log_id
                        + "doesnt exist\n Associated Entity:" + this.getClass().getSimpleName()));

        logs_repo.delete(searched_logs);
    }

}
