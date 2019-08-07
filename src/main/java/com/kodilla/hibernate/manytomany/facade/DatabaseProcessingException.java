package com.kodilla.hibernate.manytomany.facade;

public class DatabaseProcessingException extends Exception {

    public static String ERR_QUERY_NOT_COMPATIBLE = "String input not compatible with command.";

    public DatabaseProcessingException(String message){
        super(message);
    }
}
