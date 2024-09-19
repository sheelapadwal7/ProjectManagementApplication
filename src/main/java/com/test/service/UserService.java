package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.test.model.User;
import com.test.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	 
	 
	public List<User> getAllUser() {

		return userRepository.findAll();
	}

	public Optional<User> getAllUserById(Integer id) {
		return userRepository.findById(id);
	}

	 public String registerUser(User user) {
	        // Check if the username already exists
	        Optional<User> existingUser = userRepository.findByUserName(user.getUserName());
	        if (existingUser.isPresent()) {
	            return "Username already exists!";
	        }

	        // Save the user to the database with the plain password
	        userRepository.save(user);
	        
	        return "Registration successful!";
	    }

	public User createAccount(User user) {
		return userRepository.save(user);
	}

	public Optional<User> getUserByUsername(String userName) {
		return userRepository.findByUserName(userName);
	}

	public boolean existsByUserName(String userName) {
		// TODO Auto-generated method stub
		return false;
	}

}
