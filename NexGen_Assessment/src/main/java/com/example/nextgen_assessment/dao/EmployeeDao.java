package com.example.nextgen_assessment.dao;

import com.example.nextgen_assessment.entity.Employee;
import com.example.nextgen_assessment.model.EmployeeModel;
import com.example.nextgen_assessment.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class EmployeeDao {
    @Autowired
    //creating an instance of EmployeeRepository class
    private EmployeeRepository employeeRepository;

    public void insertData(String fileName) throws IOException {
        String path = "C:\\Users\\kshitij varshney\\Downloads\\" + fileName + ".csv";
        // reading the file
        BufferedReader br = new BufferedReader(new FileReader(path));
        String line = "";
        boolean firstLine = true;
        List<Employee> employees = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] arr = line.split(",");
            if (!firstLine) {
                // creating an instance of Employee class
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(arr[0]));
                employee.setFirstName(arr[1]);
                employee.setLastName(arr[2]);
                employee.setEmail(arr[3]);
                employee.setProfession(arr[4]);
                employee.setSalary(Integer.parseInt(arr[5]));
                // adding the employee object to the list
                employees.add(employee);
            } else {
                // skip the first line
                firstLine = false;
            }
        }
        // sorting the list based on the salary
        employeeRepository.saveAll(employees).sort(Comparator.comparing(Employee::getFirstName));
    }

    public EmployeeModel<List<Employee>> getTop100Salary(int pageNo) {
        // making a pageable object of size 20 and page number as pageNo
        Pageable pageable = PageRequest.of(pageNo, 20);
        // calling the method from EmployeeRepository class and fetching the list of employees
        List<Employee> employees = employeeRepository.findAll(pageable).toList();
        // sorting the list based on the salary
        return new EmployeeModel<>(employees);
    }

    public EmployeeModel<Employee> searchByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email) {
        // calling the method from EmployeeRepository class and fetching the employee object
        Employee employee = employeeRepository.findByFirstNameAndLastNameAndEmail(firstName, lastName, email);
        return new EmployeeModel<>(employee);
    }
}


