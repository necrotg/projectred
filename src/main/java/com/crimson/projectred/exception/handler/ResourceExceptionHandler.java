package com.crimson.projectred.exception.handler;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import com.crimson.projectred.exception.cust.InvalidInputException;
import com.crimson.projectred.exception.cust.NotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import com.crimson.projectred.model.APIErrorResponse;
import com.crimson.projectred.model.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorResponse> handleException(BusinessException exception, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getPathInfo()));
    }
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardErrorResponse> handleException(NotFoundException exception, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new APIErrorResponse(new Date().getTime(), HttpStatus.NOT_FOUND.value(), exception.toString(), exception.getMessage(), request.getPathInfo()));
    }
    @ExceptionHandler(InvalidInputException.class)
    public ResponseEntity<StandardErrorResponse> handleException(InvalidInputException exception, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new APIErrorResponse(new Date().getTime(), HttpStatus.BAD_REQUEST.value(), exception.toString(), exception.getMessage(), request.getPathInfo()));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> handleException(Exception exception, HttpServletRequest request){
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getPathInfo()));
    }
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<String> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String name = ex.getName();
        String type = Objects.requireNonNull(ex.getRequiredType()).getSimpleName();
        Object value = ex.getValue();
        String message = String.format("O parâmetro '%s' deve ser do tipo '%s'. Valor recebido: '%s'", name, type, value);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
