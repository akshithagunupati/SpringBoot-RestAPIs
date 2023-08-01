package com.akshithaSecondProject.restfulwebservices.User;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import com.akshithaSecondProject.restfulwebservices.JPA.PostRepository;
import com.akshithaSecondProject.restfulwebservices.JPA.UserRepository;

import jakarta.validation.Valid;

//RestAPI

@RestController
public class UserResourceJPA {
		//Create the object for the service (Which have all the logic)

	private UserRepository userRepository;
	private PostRepository postRepository;
		public UserResourceJPA(UserRepository userRepository, PostRepository postRepository) {
			super();
			this.userRepository = userRepository;
			this.postRepository = postRepository;
		}
	
	    @GetMapping("/jpa/Users")
		public List<User> retrieveAllUsers(){
			return userRepository.findAll();
		}
	
	    @GetMapping("/jpa/Users/{id}")
	    public EntityModel<User> retrieveUser(@PathVariable int id) throws UserNotFoundException{
	    	Optional<User> user= userRepository.findById(id);
	    	if(user.isEmpty())
	    		throw new UserNotFoundException("id:"+id);
	    	
	    	EntityModel<User> entityModel = EntityModel.of(user.get());
	    	WebMvcLinkBuilder link = linkTo(methodOn(this.getClass()).retrieveAllUsers()); //creating link to retrieve all users.
	    	entityModel.add(link.withRel("all-users"));
	    	return entityModel;
	    	
	    }
	    
	    
	    @PostMapping("/jpa/Users")
	    public ResponseEntity<User> createUser(@Valid @RequestBody User user) {
	        User savedUser = userRepository.save(user);
	        //This gives the path of the new user URL in the response headers.
	        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
	        		       .path("/{id}")
	        		       .buildAndExpand(savedUser.getId())
	        		       .toUri();
	        
			return ResponseEntity.created(location).build();
	    }
	    
	    @DeleteMapping("/jpa/Users/{id}")
	    public void deleteUser(@PathVariable int id)
	    {
	    	userRepository.deleteById(id);
	    }
	    
	    
	    @GetMapping("/jpa/Users/{id}/posts")
	    public List<Post> retrievePostsforUser(@PathVariable int id) throws UserNotFoundException
	    {
	    	Optional<User> user= userRepository.findById(id);
	    	
	    	if(user.isEmpty())
	    		throw new UserNotFoundException("id:"+id);
	    	return user.get().getPosts();
	    }
	    
	    @PostMapping("/jpa/Users/{id}/posts")
	    public ResponseEntity<Object> createPostForUser(@PathVariable int id,@Valid @RequestBody Post post) throws UserNotFoundException {
	    	Optional<User> user= userRepository.findById(id);
	    	
	    	if(user.isEmpty())
	    		throw new UserNotFoundException("id:"+id);
	    	post.setUser(user.get());
	    	
	    	Post savedPost = postRepository.save(post);
	    	
	    	 URI location = ServletUriComponentsBuilder.fromCurrentRequest()
      		       .path("/{id}")
      		       .buildAndExpand(savedPost.getId())
      		       .toUri();
      
		return ResponseEntity.created(location).build();
	    	
	    }	
	
}
