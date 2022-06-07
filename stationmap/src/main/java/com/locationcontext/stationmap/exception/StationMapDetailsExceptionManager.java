package com.locationcontext.stationmap.exception;

import com.locationcontext.stationmap.domain.dto.ExceptionDTO;
import com.locationcontext.stationmap.domain.dto.ResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class StationMapDetailsExceptionManager extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<Object> handleValidationException(
        Exception ex, WebRequest request) {
        ExceptionDTO exceptionDTO = new ExceptionDTO();
        exceptionDTO.setErrorDescription(ex.getMessage());
        exceptionDTO.setErrorCode("001");
        return handleExceptionInternal(ex, ResponseDTO.builder().exception(exceptionDTO).build(), new HttpHeaders(), HttpStatus.BAD_REQUEST,
            request);
    }
}
