package exception.handler;

import constant.ExceptionMessage;
import exception.cust.BusinessException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ResourceExceptionHandler {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> handleException(BusinessException exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        switch (exceptionMessage){
            case ExceptionMessage.EMAIL_EXISTS_MESSAGE:
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(new StandardError(new Date().getTime(), HttpStatus.BAD_REQUEST.value(),ExceptionMessage.EMAIL_EXISTS_MESSAGE, exception.getMessage(),request.getPathInfo()));

            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new StandardError(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(),request.getPathInfo()));
        }
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<StandardError> handleException(Exception exception, HttpServletRequest request){

        String exceptionMessage = exception.getMessage();

        switch (exceptionMessage){
            default:
                return ResponseEntity
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new StandardError(new Date().getTime(), HttpStatus.INTERNAL_SERVER_ERROR.value(),ExceptionMessage.INTERNAL_SERVER_ERROR, exception.getMessage(),request.getPathInfo()));
        }
    }

}
