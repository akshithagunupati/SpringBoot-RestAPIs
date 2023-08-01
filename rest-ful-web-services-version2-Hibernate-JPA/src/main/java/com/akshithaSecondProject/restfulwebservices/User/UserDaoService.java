package com.akshithaSecondProject.restfulwebservices.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

@Component   
public class UserDaoService {
		private static List<User> users = new ArrayList<>(); //list of type User class.
		private static int id = 0;
		static {
			users.add(new User(id++,"Adam",LocalDate.now().minusYears(25)));
			users.add(new User(id++,"Bunny",LocalDate.now().minusYears(15)));
			users.add(new User(id++,"Camel",LocalDate.now().minusYears(20)));
		}
		
		
		//Method to find all users
		public List<User> findAll(){
			return users;
		}

		public User findOne(int id) {
			Predicate<? super User> predicate = user -> user.getId().equals(id);
			return users.stream().filter(predicate).findFirst().orElse(null);
		}
		//Method to add new user
		public User save(User user)
		{
			user.setId(id++);
			users.add(user);
			return user;
		}
		
		public void deleteByUserId(int id)
		{
			Predicate<? super User> predicate = user -> user.getId().equals(id);
			users.removeIf(predicate);
		}
		
		
		
}
