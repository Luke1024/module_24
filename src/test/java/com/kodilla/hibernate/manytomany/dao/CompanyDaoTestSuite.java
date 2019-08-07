package com.kodilla.hibernate.manytomany.dao;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.task.Task;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CompanyDaoTestSuite {
    @Autowired
    CompanyDao companyDao;
    @Autowired
    EmployeeDao employeeDao;

    @Test
    public void testSaveManyToMany(){
        //Given
        Employee johnSmith = new Employee(1,"John", "Smith");
        Employee stephanieClarckson = new Employee(2,"Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee(3,"Linda", "Kovalsky");

        Company softwareMachine = new Company(1,"Software Machine");
        Company dataMaesters = new Company(2,"Data Maesters");
        Company greyMatter = new Company(3,"Grey Matter");

        softwareMachine.getEmployees().add(johnSmith);
        dataMaesters.getEmployees().add(stephanieClarckson);
        dataMaesters.getEmployees().add(lindaKovalsky);
        greyMatter.getEmployees().add(johnSmith);
        greyMatter.getEmployees().add(lindaKovalsky);

        johnSmith.getCompanies().add(softwareMachine);
        johnSmith.getCompanies().add(greyMatter);
        stephanieClarckson.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(dataMaesters);
        lindaKovalsky.getCompanies().add(greyMatter);

        //When
        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //Then
        Assert.assertNotEquals(0, softwareMachineId);
        Assert.assertNotEquals(0, dataMaestersId);
        Assert.assertNotEquals(0, greyMatterId);

        //CleanUp
        try {
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        } catch (Exception e) {
            //do nothing
        }
    }

    @Test
    public void testFindEmployeesByLastname(){
        //Given
        Employee johnSmith = new Employee(1,"John", "Smith");
        Employee stephanieClarckson = new Employee(2,"Stephanie", "Clarckson");
        Employee lindaKovalsky = new Employee(3,"Linda", "Kovalsky");

        employeeDao.save(johnSmith);
        employeeDao.save(stephanieClarckson);
        employeeDao.save(lindaKovalsky);

        int johnSmithId = johnSmith.getId();
        int stephanieClarcksonId = stephanieClarckson.getId();
        int lindaKovalskyId = lindaKovalsky.getId();

        //When
        List<Employee> employees1 = employeeDao.findEmployeesByLastname("Smith");
        List<Employee> employees2 = employeeDao.findEmployeesByLastname("Clarckson");
        List<Employee> employees3 = employeeDao.findEmployeesByLastname("Kovalsky");

        //Then
        try {
            Assert.assertEquals(employees1.get(0).getFirstname(), johnSmith.getFirstname());
            Assert.assertEquals(employees2.get(0).getFirstname(), stephanieClarckson.getFirstname());
            Assert.assertEquals(employees3.get(0).getFirstname(), lindaKovalsky.getFirstname());
        } finally {
            //CleanUp
            employeeDao.deleteById(johnSmithId);
            employeeDao.deleteById(stephanieClarcksonId);
            employeeDao.deleteById(lindaKovalskyId);
        }
    }

    @Test
    public void testFindACompanyWithTheBeginningOfTheName(){
        //Given
        Company softwareMachine = new Company(1,"Software Machine");
        Company dataMaesters = new Company(2,"Data Maesters");
        Company greyMatter = new Company(3,"Grey Matter");

        companyDao.save(softwareMachine);
        int softwareMachineId = softwareMachine.getId();
        companyDao.save(dataMaesters);
        int dataMaestersId = dataMaesters.getId();
        companyDao.save(greyMatter);
        int greyMatterId = greyMatter.getId();

        //When
        List<Company> sofSearch = companyDao.findACompanyWithTheBeginningOfTheName("Sof" + "%");
        List<Company> datSearch = companyDao.findACompanyWithTheBeginningOfTheName("Dat" + "%");
        List<Company> greSearch = companyDao.findACompanyWithTheBeginningOfTheName("Gre" + "%");

        //Then
        try {
            Assert.assertEquals(sofSearch.get(0).getName(), "Software Machine");
            Assert.assertEquals(datSearch.get(0).getName(), "Data Maesters");
            Assert.assertEquals(greSearch.get(0).getName(), "Grey Matter");
        } finally {
            //CleanUp
            companyDao.deleteById(softwareMachineId);
            companyDao.deleteById(dataMaestersId);
            companyDao.deleteById(greyMatterId);
        }
    }
}