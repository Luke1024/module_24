package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Employee;
import org.springframework.stereotype.Component;

@Component
public class FacadeMessages {
    public void employeeSaved(Employee employee){
        System.out.println("Employee: " + employee.getFirstname() + " " + employee.getLastname() +
                " succesfully saved in database.");
    }
}