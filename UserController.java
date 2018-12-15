package com.rest.app.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rest.app.model.User;
import com.rest.app.repository.UserRepository;

@RestController
@RequestMapping(value="/rest/users")
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@GetMapping(value="/all")
	public List<User> getAll() {
		return userRepository.findAll();
	}
	
	
	@PostMapping(value="/save")
	public User addUser(@RequestBody final User user)
	{
		return userRepository.save(user);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void deleteUser(@PathVariable Integer id)
	{
		 userRepository.deleteById(id);
	}
	
	@PutMapping(value="/update/{id}")
	public ResponseEntity<Object> modifyUser(@RequestBody final User user,@PathVariable Integer id) {
		
		Optional<User> userOptional = userRepository.findById(id);
		
		if (!userOptional.isPresent())
			return ResponseEntity.notFound().build();

		user.setId(id);
		
		userRepository.save(user);
		
		return ResponseEntity.noContent().build();
		
	}
}
