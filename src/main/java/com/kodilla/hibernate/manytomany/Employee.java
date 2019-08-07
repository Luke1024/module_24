package com.kodilla.hibernate.manytomany;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@NamedNativeQuery(
        name = "Employee.findEmployeesByLastname",
        query = "SELECT * FROM employees WHERE lastname LIKE CONCAT ('%', :LASTNAME, '%')",
        resultClass = Employee.class
)

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    private int id;
    private String firstname;
    private String lastname;
    private List<Company> companiesList = new ArrayList<>();

    public Employee() {
    }

    public Employee(int id, String firstname, String lastname) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    @Id
    @NotNull
    @Column(name = "EMPLOYEE_ID", unique = true)
    public int getId() {
        return id;
    }

    @NotNull
    @Column(name = "FIRSTNAME")
    public String getFirstname() {
        return firstname;
    }

    @NotNull
    @Column(name = "LASTNAME")
    public String getLastname() {
        return lastname;
    }

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "JOIN_COMPANY_EMPLOYEE",
            joinColumns = {@JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "EMPLOYEE_ID")},
            inverseJoinColumns = {@JoinColumn(name = "COMPANY_ID", referencedColumnName = "COMPANY_ID")}
    )

    public List<Company> getCompaniesList() {
        return companiesList;
    }

    public void setCompaniesList(List<Company> companiesList) {
        this.companiesList = companiesList;
    }

    private void setId(int id) {
        this.id = id;
    }

    private void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    private void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee)) return false;
        Employee employee = (Employee) o;
        return id == employee.id &&
                firstname.equals(employee.firstname) &&
                lastname.equals(employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstname, lastname, companiesList);
    }
}