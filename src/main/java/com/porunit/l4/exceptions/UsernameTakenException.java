package com.porunit.l4.exceptions;

public class UsernameTakenException extends Throwable {
    public UsernameTakenException(String usernameTaken) {
        super(usernameTaken);
    }
}
