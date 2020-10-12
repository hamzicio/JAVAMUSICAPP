package com.employeeportal.demo.user.repository;

import com.employeeportal.demo.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

   User getUserById(Integer id);
   User getUserByUsername(String username);

}
