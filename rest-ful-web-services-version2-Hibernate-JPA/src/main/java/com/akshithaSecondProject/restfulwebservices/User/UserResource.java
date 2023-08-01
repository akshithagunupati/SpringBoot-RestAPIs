package com.akshithaSecondProject.restfulwebservices.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import jakarta.validation.Valid;

//RestAPI

//@RestController
public class UserResource {
		//Create the object for the service (Which have all the logic)
	
	private UserDaoService Service;

		public UserResource(UserDaoService service) {
			super();
			Service = service;
		}
	
	    @GetMapping("/Users")
		public List<User> retrieveAllUsers(){
			return Service.findAll();
		}
	
	    @GetMapping("/Users/{id}")
	    public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException{
	    	User user= Service.findOne(id);
	    	if(user==null)
	    		throw new UserNotFoundException("id:"+id);
	    	
	    	EntityModel<User> entityModel = EntityModel.of(user);
	    	WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //creating link to retrieve all users.
	    	entityModel.add(link.withRel("all-users"));
	    	return entityModel;
	    	
	    }
	    
	    
	    @PostMapping("/Users")
	    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	        User savedUser = Service.save(user);
	        //This gives the path of the new user URL in the response headers.
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	        		       .path("/{id}")
	        		       .buildAndExpand(savedUser.getId())
	        		       .toUri();
	        
			return ResponseEntity.created(location).build();
	    }
	    
	    @DeleteMapping("/Users/{id}")
	    public void deleteUser(@PathVariable int id)
	    {
	    	Service.deleteByUserId(id);
	    }
	
}
