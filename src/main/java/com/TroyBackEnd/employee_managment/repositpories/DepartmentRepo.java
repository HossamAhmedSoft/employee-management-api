package com.TroyBackEnd.employee_managment.repositpories;

import com.TroyBackEnd.employee_managment.Entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DepartmentRepo extends JpaRepository<Department, UUID> {


}
