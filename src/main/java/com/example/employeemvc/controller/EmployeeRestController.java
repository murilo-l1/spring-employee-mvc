package com.example.employeemvc.controller;

import com.example.employeemvc.entity.Employee;
import com.example.employeemvc.exception.EmployeeNotFoundException;
import com.example.employeemvc.exception.InvalidEmployeeCreationException;
import com.example.employeemvc.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeRestController {

    private final EmployeeService services;

    @Autowired
    public EmployeeRestController(EmployeeService services) {
        this.services = services;
    }

    @GetMapping("/list")
    public String getAllEmployees(Model model){
        List<Employee> retrievedEmployees = services.findAll();
        model.addAttribute("retrievedEmployees", retrievedEmployees);

        return "employees/employees-list";
    }

    @GetMapping("/showFormForAdd")
    public String showAddEmployeeForm(Model model){
        Employee toBeAddEmployee = new Employee();

        model.addAttribute("employee", toBeAddEmployee);

        return "employees/employee-form";
    }

    @PostMapping("/save")
    public String addEmployee(@Valid @ModelAttribute("employee") Employee formEmployee, BindingResult result) throws InvalidEmployeeCreationException {
        if(result.hasErrors()){
            return "employees/employee-form";
        }
        // save new Employee if fields are correctly set
        services.saveEmployee(formEmployee);
        return "redirect:/employees/list";
    }

    @GetMapping("/showFormToUpdate")
    public String showFormForUpdate(@RequestParam("employeeId") Integer employeeId, Model model) throws EmployeeNotFoundException{
        // get Employee from db
        Employee toUpdateEmployee = services.findEmployeeById(employeeId);

        //add employee to form
        model.addAttribute("employee", toUpdateEmployee);

        // send user back to form, so he can update selected Employee
        return "employees/employee-form";
    }

    @GetMapping("/deleteEmployeeById")
    public String deleteEmployee(@RequestParam("employeeId") Integer employeeId) throws EmployeeNotFoundException{
        services.deleteEmployeeById(employeeId);
        return "redirect:/employees/list";
    }

}