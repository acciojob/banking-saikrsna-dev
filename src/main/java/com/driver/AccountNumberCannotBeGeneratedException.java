package com.driver;

public class AccountNumberCannotBeGeneratedException extends Exception{
    public AccountNumberCannotBeGeneratedException(String message) {
        super(message);
    }
}
