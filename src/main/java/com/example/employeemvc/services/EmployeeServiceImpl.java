package com.example.employeemvc.services;

import com.example.employeemvc.dao.EmployeeRepository;
import com.example.employeemvc.entity.Employee;
import com.example.employeemvc.exception.EmployeeNotFoundException;
import com.example.employeemvc.exception.InvalidEmployeeCreationException;
import org.springframework.beans.InvalidPropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository repository;

    @Autowired
    public EmployeeServiceImpl(EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Employee> findAll() {
        return repository.findAll();
    }

    @Override
    public Employee findEmployeeById(Integer employeeId) throws EmployeeNotFoundException{
        Optional<Employee> optionalEmployee = repository.findById(employeeId);
        if(optionalEmployee.isPresent()){
            return optionalEmployee.get();
        }else{
            throw new EmployeeNotFoundException("Not a given employee with this id: " + employeeId);
        }
    }

    @Override
    public Employee saveEmployee(Employee employee) throws InvalidEmployeeCreationException {
       Employee tempEmployee = repository.save(employee);
       if(tempEmployee.getEmployeeFirstName() == null){
           throw new InvalidEmployeeCreationException("The Employee Information should contain all elements");
       }
       return tempEmployee;
    }

    @Override
    public void deleteEmployeeById(Integer employeeId) throws EmployeeNotFoundException{
        if(repository.existsById(employeeId)){
            repository.deleteById(employeeId);
        }else{
            throw new EmployeeNotFoundException("Can't delete an employee with this id: " + employeeId);
        }
    }
}
