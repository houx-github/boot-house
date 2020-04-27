package com.etoak.exception;

/**
 * 用户登陆异常
 */
public class UserLoginException extends RuntimeException {
    public UserLoginException(String message){
        super(message);
    }
}
