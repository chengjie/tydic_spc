package com.tydic.spc.exception;

/**
 * @author hezhong
 */
public class JsonResultException extends RuntimeException {
    public JsonResultException() {
    }

    public JsonResultException(String message) {
        super(message);
    }

    public JsonResultException(String message, Throwable cause) {
        super(message, cause);
    }

    public JsonResultException(Throwable cause) {
        super(cause);
    }
}
