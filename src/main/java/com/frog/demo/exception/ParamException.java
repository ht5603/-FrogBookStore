package com.frog.demo.exception;

import java.math.BigDecimal;

/**
 * This Exception is for parameter validation.
 */
public class ParamException extends RuntimeException{

    public ParamException() {
        super();
    }

    public ParamException(String message, Throwable cause) {
        super(message, cause);
    }

    public ParamException(String message) {
        super(message);
    }

    public ParamException(Throwable cause) {
        super(cause);
    }

}
