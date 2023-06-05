package com.medicoApp.medicoApp.exception;

public class UserDoesntExistException  extends RuntimeException{
    private String userName;

    public UserDoesntExistException(String userName) {
        this.userName = userName;
    }
    public String getMessage(){
//        return (String.format("Użytkownik %s nie istnieje.", userName));
        return "Niepoprawny użytkownik lub hasło";
    }
}
