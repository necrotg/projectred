package com.crimson.projectred.exception.handler;

import com.crimson.projectred.constant.ExceptionMessage;
import com.crimson.projectred.exception.cust.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import com.crimson.projectred.model.APIErrorResponse;
import com.crimson.projectred.model.StandardErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorResponse> handleException(BusinessException exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        switch (exceptionMessage){
            case ExceptionMessage.EMAIL_EXISTS_MESSAGE:
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new APIErrorResponse(new Date().getTime(), HttpStatus.NOT_FOUND.value(),ExceptionMessage.EMAIL_EXISTS_MESSAGE, exception.getMessage(),request.getPathTranslated()));
            case ExceptionMessage.CUSTOMER_NOT_FOUND:
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new APIErrorResponse(new Date().getTime(), HttpStatus.NOT_FOUND.value(),ExceptionMessage.CUSTOMER_NOT_FOUND, exception.getMessage(),request.getPathInfo()));
            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(),request.getPathInfo()));
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> handleException(Exception exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        switch (exceptionMessage){
            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(),request.getPathInfo()));
        }
    }

}
