package com.xebialabs;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

// define an interface that extends the JPA Repository interface to enable methods on H2 Database

@Repository
public interface EmployeeRepo extends JpaRepository<Employee, String> {
}
