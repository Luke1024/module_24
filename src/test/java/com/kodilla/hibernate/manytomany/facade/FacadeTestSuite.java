package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class FacadeTestSuite {

    @Autowired
    private Facade facade;

    @Test
    public void testFindEmployeesWithPartOfTheName() {
        //Given
        Employee johnSmith = new Employee(1, "John", "Smith");
        Employee stephanieClarckson = new Employee(2, "Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee(3, "Linda", "Kovalsky");

        facade.execute(asList(johnSmith, stephanieClarckson, lindaKovalsky), Command.ADD);

        //When
        ResponseDTO responseDTO = facade.execute(asList("Smith", "Clarckson", "Kovalsky"), Command.FIND_EMPLOYEE);

        //Then
        assertTrue(employeeIsInDatabase(responseDTO.getResponses().get(0), johnSmith));
        assertTrue(employeeIsInDatabase(responseDTO.getResponses().get(1), stephanieClarckson));
        assertTrue(employeeIsInDatabase(responseDTO.getResponses().get(2), lindaKovalsky));

        //CleanUp

        facade.execute(asList(johnSmith, stephanieClarckson, lindaKovalsky), Command.DELETE);
    }

    private boolean employeeIsInDatabase(Response response, Employee employee) {
        return response.getEntities().stream().filter(e -> e.equals(employee)).collect(Collectors.toList()).size()>0;
    }

    @Test
    public void testFindCompaniesWithPartOfTheName() {
        //Given
        Company softwareMachine = new Company(1,"Software Machine");
        Company dataMaesters = new Company(2,"Data Maesters");
        Company greyMatter = new Company(3,"Grey Matter");

        facade.execute(asList(softwareMachine, dataMaesters, greyMatter), Command.ADD);
        //When
        ResponseDTO responseDTO = facade.execute(asList("Software Machine", "Data Maesters", "Grey Matter"), Command.FIND_COMPANY);

        //Then
        assertTrue(companyIsInDatabase(responseDTO.getResponses().get(0), softwareMachine));
        assertTrue(companyIsInDatabase(responseDTO.getResponses().get(1), dataMaesters));
        assertTrue(companyIsInDatabase(responseDTO.getResponses().get(2), greyMatter));

        //CleanUp
        facade.execute(asList(softwareMachine, dataMaesters, greyMatter), Command.DELETE);
    }

    private boolean companyIsInDatabase(Response response, Company company){
        return response.getEntities().stream().filter(c -> c.equals(company)).collect(Collectors.toList()).size()>0;
    }
}