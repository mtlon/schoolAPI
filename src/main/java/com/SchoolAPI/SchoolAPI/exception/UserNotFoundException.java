package com.SchoolAPI.SchoolAPI.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(int id) {
        super("Couldn't find user with id " + id);
    }
}
