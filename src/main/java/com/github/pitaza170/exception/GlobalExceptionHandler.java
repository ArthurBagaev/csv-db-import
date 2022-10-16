package com.github.pitaza170.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Objects;

import static com.github.pitaza170.common.Constants.*;

@Slf4j(topic = "GLOBAL_EXCEPTION_HANDLER")
@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @Value("${reflecting.trace:false}")
    private boolean printStackTrace;

    @Override
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(HttpStatus.UNPROCESSABLE_ENTITY.value(), VALIDATION_ERROR);

        for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
            errorResponse.addValidationError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

    public ResponseEntity<Object> handleEntityNotFoundException(javax.persistence.EntityNotFoundException ex,
                                                                WebRequest request) {
        log.error(ENTITY_NOT_FOUND, ex);
        return buildErrorResponse(ex, ENTITY_NOT_FOUND, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleEntityNotFoundException(com.github.pitaza170.exception.EntityNotFoundException ex,
                                                                WebRequest request) {
        log.error(ENTITY_NOT_FOUND, ex);
        return buildErrorResponse(ex, HttpStatus.NOT_FOUND, request);
    }

    private ResponseEntity<Object> buildErrorResponse(EntityNotFoundException ex,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {
        return buildErrorResponse(ex, ex.getMessage(), httpStatus, request);
    }

    private ResponseEntity<Object> buildErrorResponse(Exception ex,
                                                      String message,
                                                      HttpStatus httpStatus,
                                                      WebRequest request) {

        ErrorResponse errorResponse = new ErrorResponse(httpStatus.value(), message);
        if (printStackTrace && isTraceOn(request)) {
            errorResponse.setStackTrace(ExceptionUtils.getStackTrace(ex));
        }
        return ResponseEntity.status(httpStatus).body(errorResponse);
    }

    private boolean isTraceOn(WebRequest request) {
        String[] value = request.getParameterValues(TRACE);
        return Objects.nonNull(value)
                && value.length > 0
                && value[0].contentEquals("true");
    }


}
