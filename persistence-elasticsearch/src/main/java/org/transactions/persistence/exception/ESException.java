package org.transactions.persistence.exception;

public class ESException extends RuntimeException {
    public ESException(String message, Exception cause) {
        super(message, cause);
    }
}
