package com.kodilla.hibernate.manytomany.facade;

import com.kodilla.hibernate.manytomany.Company;
import com.kodilla.hibernate.manytomany.Employee;
import com.kodilla.hibernate.manytomany.dao.CompanyDao;
import com.kodilla.hibernate.manytomany.dao.EmployeeDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class Facade {
    private static final Logger LOGGER = LoggerFactory.getLogger(Facade.class);

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private CompanyDao companyDao;

    public <T> ResponseDTO execute(List<T> objects, Command command) {
        return executeCommand(objects, command);
    }

    private <T> ResponseDTO executeCommand(List<T> objects, Command command) {
        ResponseDTO responseDTO = new ResponseDTO();

        for (Object object : objects) {
            if (object instanceof Employee) {
                processingEmployee((Employee) object, command);
            }
            if (object instanceof Company) {
                processingCompany((Company) object, command);
            }
            if (object instanceof String) {
                Response response = searchDatabase((String) object, command);
                response.setCommand(command);
                response.setQuery((String) object);
                responseDTO.getResponses().add(response);
            }
        }
        return responseDTO;
    }

    private void processingEmployee(Employee employee, Command operator) {
        if (operator == Command.ADD) {
            LOGGER.info("Adding employee " + employee.getFirstname(), employee.getLastname());
            saveEmployee(employee);
        }
        if (operator == Command.DELETE) {
            LOGGER.info("Deleting employee " + employee.getFirstname(), employee.getLastname());
            deleteEmployee(employee);
        }
    }

    private void processingCompany(Company company, Command operator) {
        if (operator == Command.ADD) {
            LOGGER.info("Adding company " + company.getName());
            saveCompany(company);
        }
        if (operator == Command.DELETE) {
            LOGGER.info("Deleting company " + company.getName());
            deleteCompany(company);
        }
    }

    private Response searchDatabase(String string, Command operator) {
        Response response = new Response();

        if (operator == Command.FIND_EMPLOYEE) {
            response.setEntities(findEmployees(string));
        }
        if (operator == Command.FIND_COMPANY) {
            response.setEntities(findCompanies(string));
        }
        return response;
    }


    private void saveEmployee(Employee employee) {
        employeeDao.save(employee);
    }

    private void deleteEmployee(Employee employee) {
        employeeDao.deleteById(employee.getId());
    }


    private void saveCompany(Company company) {
        companyDao.save(company);
    }

    private void deleteCompany(Company company) {
        companyDao.deleteById(company.getId());
    }


    private List<Employee> findEmployees(String string) {
        return employeeDao.findEmployeesByLastname(string);
    }
    private List<Company> findCompanies(String string){
        return companyDao.findACompanyWithTheBeginningOfTheName(string);
    }
}