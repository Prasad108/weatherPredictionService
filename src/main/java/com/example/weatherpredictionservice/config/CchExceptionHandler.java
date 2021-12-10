package com.example.weatherpredictionservice.config;

import com.example.weatherpredictionservice.DTO.ErrorDetails;
import com.example.weatherpredictionservice.util.CityNotFoundException;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.annotation.Priority;
import java.util.Date;

@RestControllerAdvice
@Priority(Ordered.HIGHEST_PRECEDENCE)
@ResponseBody
public class CchExceptionHandler extends ResponseEntityExceptionHandler {



    /**
     * Handle resource not found exceptions
     *
     * @param ex      : Exception
     * @return : 404 resource not found
     */
    @ExceptionHandler({CityNotFoundException.class})
    protected ResponseEntity<?> resourceNotFoundExceptionHandler(CityNotFoundException ex) {
        ErrorDetails exceptionResponse = new ErrorDetails();
        exceptionResponse.setStatus(HttpStatus.NOT_FOUND);
        exceptionResponse.setMessage("City not found");
        exceptionResponse.setTimestamp(new Date());
        return buildResponseEntity(exceptionResponse);
    }

    private ResponseEntity<Object> buildResponseEntity(ErrorDetails exceptionResponse) {
        return new ResponseEntity<>(exceptionResponse, exceptionResponse.getStatus());
    }


}
