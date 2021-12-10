package com.example.weatherpredictionservice.DTO;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.Date;

@Data
public class ErrorDetails {
    private HttpStatus status;
    private Date timestamp;
    private String message;
    private String details;
}
