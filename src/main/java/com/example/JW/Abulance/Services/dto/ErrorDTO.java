package com.example.JW.Abulance.Services.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class ErrorDTO {
    private Integer code;
    private String summary;
    private String table;
    private String message;
    private LocalDateTime time;

    public ErrorDTO() {}

    public ErrorDTO(Integer code, String summary, String message, LocalDateTime time) {
        this.code = code;
        this.summary = summary;
        this.message = message;
        this.time = time;
    } 

    public ErrorDTO(Integer code, String summary, String table, String message, LocalDateTime time) {
        this.code = code;
        this.summary = summary;
        this.table = table;
        this.message = message;
        this.time = time;
    } 
}
