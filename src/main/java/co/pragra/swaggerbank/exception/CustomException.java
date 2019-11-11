package co.pragra.swaggerbank.exception;

public class CustomException extends RuntimeException{
    public CustomException(Object className) {
        super(((Class) className).getName().substring(((Class) className).getName().lastIndexOf('.')+1)+ " not found");
    }
}
