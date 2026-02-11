package com.crimson.projectred.exception.cust;

public class BusinessException extends RuntimeException{
    BusinessException() {
        super();
    }
    public BusinessException(String message){
        super(message);
    }
}
