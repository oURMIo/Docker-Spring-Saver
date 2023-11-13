package com.example.saver.dto;

import lombok.Getter;
import lombok.ToString;

import java.util.Date;

@Getter
@ToString
public class AppErrorDto {
    private final int status;
    private final String message;
    private final Date timestamp;

    public AppErrorDto(int status, String message) {
        this.status = status;
        this.message = message;
        this.timestamp = new Date();
    }
}
