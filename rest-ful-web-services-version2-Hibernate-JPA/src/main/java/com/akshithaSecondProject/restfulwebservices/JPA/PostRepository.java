package com.akshithaSecondProject.restfulwebservices.JPA;

import org.springframework.data.jpa.repository.JpaRepository;

import com.akshithaSecondProject.restfulwebservices.User.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {

}
