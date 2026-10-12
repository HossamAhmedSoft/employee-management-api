package com.TroyBackEnd.employee_managment.repositpories;

import com.TroyBackEnd.employee_managment.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, UUID> {


}
