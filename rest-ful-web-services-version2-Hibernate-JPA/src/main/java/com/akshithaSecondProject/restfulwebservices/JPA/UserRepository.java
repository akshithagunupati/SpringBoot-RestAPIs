package com.akshithaSecondProject.restfulwebservices.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshithaSecondProject.restfulwebservices.User.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}
