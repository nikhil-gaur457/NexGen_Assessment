package com.example.nextgen_assessment.controller;

import com.example.nextgen_assessment.dao.EmployeeDao;
import com.example.nextgen_assessment.entity.Employee;
import com.example.nextgen_assessment.model.EmployeeModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    //creating an instance of EmployeeDao class
    EmployeeDao employeeDao;

    //method to check if the server is up and running
    @GetMapping("/check")
    public String check() {
        return "Working";
    }

    // method to insert data into the database
    @PostMapping("/insertData/{fileName}")
    public String insertData(@PathVariable String fileName) throws IOException {
        //calling the insertData method of EmployeeDao class
        employeeDao.insertData(fileName);
        return "Data inserted";
    }

    @GetMapping("/getTop100Salary/{pageNo}")
    public EmployeeModel<List<Employee>> getTop10Salary(@PathVariable int pageNo) {
        //calling the method from EmployeeDao class
        return employeeDao.getTop100Salary(pageNo);
    }

    @GetMapping("/searchByFirstNameAndLastNameAndEmail/{firstName}/{lastName}/{email}")
    public EmployeeModel<Employee> searchByFirstNameAndLastNameAndEmail(@PathVariable String firstName, @PathVariable String lastName, @PathVariable String email) {
        //calling the method from EmployeeDao class
        return employeeDao.searchByFirstNameAndLastNameAndEmail(firstName, lastName, email);
    }
}

