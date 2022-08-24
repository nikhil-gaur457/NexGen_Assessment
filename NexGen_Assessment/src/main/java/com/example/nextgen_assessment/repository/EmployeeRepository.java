package com.example.nextgen_assessment.repository;

import com.example.nextgen_assessment.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {


    @Query("select e from Employee e where e.firstName= :firstName and e.lastName =:lastName and e.email = :email")
    Employee findByFirstNameAndLastNameAndEmail(String firstName, String lastName, String email);
}
