package com.example.project.exceptions;

public class NotAllowedToLoggIn extends Exception{
    public NotAllowedToLoggIn(String errorMessage){
        super(errorMessage);
    }
}
