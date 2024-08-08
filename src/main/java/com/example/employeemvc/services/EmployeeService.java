package com.example.employeemvc.services;

import com.example.employeemvc.entity.Employee;
import com.example.employeemvc.exception.EmployeeNotFoundException;
import com.example.employeemvc.exception.InvalidEmployeeCreationException;

import java.util.List;

public interface EmployeeService {

    List<Employee> findAll();
    Employee findEmployeeById(Integer employeeId) throws EmployeeNotFoundException;
    Employee saveEmployee(Employee employee) throws InvalidEmployeeCreationException;
    void deleteEmployeeById(Integer employeeId) throws EmployeeNotFoundException;


}
