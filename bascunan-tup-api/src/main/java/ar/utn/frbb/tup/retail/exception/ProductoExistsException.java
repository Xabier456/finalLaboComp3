package ar.utn.frbb.tup.retail.exception;

public class ProductoExistsException extends RuntimeException {
    public ProductoExistsException(String message) {
        super(message);
    }
} 