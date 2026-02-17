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
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Date;
import java.util.Objects;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardErrorResponse> handleException(BusinessException exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        return switch (exceptionMessage) {
            case ExceptionMessage.EMAIL_EXISTS_MESSAGE -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorResponse(new Date().getTime(), HttpStatus.NOT_FOUND.value(), ExceptionMessage.EMAIL_EXISTS_MESSAGE, exception.getMessage(), request.getPathTranslated()));
            case ExceptionMessage.CUSTOMER_NOT_FOUND -> ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new APIErrorResponse(new Date().getTime(), HttpStatus.NOT_FOUND.value(), ExceptionMessage.CUSTOMER_NOT_FOUND, exception.getMessage(), request.getPathInfo()));
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getPathInfo()));
        };
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardErrorResponse> handleException(Exception exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        return switch (exceptionMessage) {
            default -> ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new APIErrorResponse(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(), ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(), request.getPathInfo()));
        };
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
