package com.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		 List<User> user=userService.getAllUser();
		 return ResponseEntity.ok(user);		 			 
	}
	
	
	 @GetMapping("/{id}")
	    public ResponseEntity<?> getUserById(@PathVariable Integer id) {
	        Optional<User> user = userService.getAllUserById(id);
	        if (user.isPresent()) {
	            return ResponseEntity.ok().body(user.get());
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found with id: " + id);
	        }
	    }

		/*
		 * @PostMapping("/register") public ResponseEntity<?> registerUser(@RequestBody
		 * User user) { User registeredUser = userService.registerUser(user); return
		 * ResponseEntity.status(HttpStatus.CREATED).
		 * body(" User Register successfully with id: " + registeredUser.getId()); }
		 */
	    
		/*
		 * @PostMapping("/registers") public ResponseEntity<?>
		 * registerUser(@Valid @RequestBody User user) { try { User newUser =
		 * userService.registerUser(user); return new ResponseEntity<>(newUser,
		 * HttpStatus.CREATED); } catch (RuntimeException e) { return new
		 * ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST); } }
		 */
	    
	 @PostMapping("/register")
	    public ResponseEntity<Map<String, String>> register(@Valid @RequestBody User user, BindingResult bindingResult) {
	        // Handle validation errors
	        if (bindingResult.hasErrors()) {
	            Map<String, String> errors = new HashMap<>();
	            bindingResult.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	            return ResponseEntity.badRequest().body(errors);
	        }

	        // Check if the user already exists
	        if (userService.existsByUserName(user.getUserName())) {
	            Map<String, String> error = new HashMap<>();
	            error.put("username", "Username already exists.");
	            return ResponseEntity.badRequest().body(error);
	        }

	        // Save the user
	        userService.createAccount(user);

	        // Prepare success response
	        Map<String, String> response = new HashMap<>();
	        response.put("message", "User registered successfully.");
	        return ResponseEntity.ok(response);
	    }


	    @GetMapping("/{username}")
	    public ResponseEntity<?> getUserByUsername(@PathVariable String username) {
	        Optional<User> user = userService.getUserByUsername(username);
	        if (user != null) {
	            return new ResponseEntity<>(user, HttpStatus.OK);
	        } else {
	            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	        }
	    }
	    
	    @PostMapping("/createAccount")
	    public ResponseEntity<?> createAccount(@RequestBody User user) {
	        User userAccount = userService.createAccount(user);
	        return ResponseEntity.status(HttpStatus.CREATED).body(" User account created successfully with id: " + userAccount.getId());
	    }
		

}
