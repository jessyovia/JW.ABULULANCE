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

import com.example.JW.Abulance.Services.entity.Dispatch;
import com.example.JW.Abulance.Services.service.implementations.DispatchServiceImpl;

@RestController
@RequestMapping("/api/v1/dispatches")
public class DispatchController {
    private final DispatchServiceImpl dispatch_service;

    public DispatchController(DispatchServiceImpl dispatch_service) {
        this.dispatch_service = dispatch_service;
    }

    @GetMapping("/")
    public ResponseEntity<List<Dispatch>> getAllDispatches() {
        List<Dispatch> all_dispatches = dispatch_service.getAllDispatches();

        return new ResponseEntity<List<Dispatch>>(all_dispatches, HttpStatus.OK);
    }

    // POST a dispatch record
    @PostMapping("/")
    public ResponseEntity<Dispatch> postNewDispatchRecord(@RequestBody Dispatch new_record) {
        Dispatch added_record = dispatch_service.postNewDispatch(new_record);

        return new ResponseEntity<Dispatch>(added_record, HttpStatus.CREATED);
    }

    // PUT a dispatch record
    @PutMapping("/{id}")
    public ResponseEntity<Dispatch> editStaff(
            @PathVariable("id") int dispatch_id,
            Dispatch dispatch_edit) {
        return new ResponseEntity<Dispatch>(
                dispatch_service.putExistingDispatch(dispatch_id, dispatch_edit),
                HttpStatus.OK);
    }

    // DELETE a dispatch record
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDispatch(@PathVariable("id") int dispatch_id) {
        dispatch_service.deleteDispatch(dispatch_id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
