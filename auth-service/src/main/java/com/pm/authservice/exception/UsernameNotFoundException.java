package com.pm.authservice.exception;

public class UsernameNotFoundException extends RuntimeException
{
    private String message ;

    public UsernameNotFoundException(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
