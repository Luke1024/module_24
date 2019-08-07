package com.kodilla.hibernate.manytomany.facade;

public class DatabaseProcessingException extends Exception{
    public static String EMPLOYEE_NOT_FOUND = "Employee not found.";
    public static String EMPLOYEE_SAVING_ERROR = "Employee saving error.";
    public static String INCORRECT_ENTRY_TYPE = "Incorrect entry type.";
    public static String INCORRECT_OPERATOR = "Incorrect operator.";

    public DatabaseProcessingException(String message) {
        super(message);
    }
}