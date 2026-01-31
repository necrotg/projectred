package exception.cust;

public class BusinessException extends RuntimeException{
    BusinessException() {
        super();
    }
    public BusinessException(String message){
        super(message);
    }
}
