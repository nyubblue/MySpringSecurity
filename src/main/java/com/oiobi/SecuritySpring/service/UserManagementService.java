package com.oiobi.SecuritySpring.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oiobi.SecuritySpring.entities.User;
import com.oiobi.SecuritySpring.repository.UserRepository;

@Service
public class UserManagementService {

	@Autowired
	private UserRepository userRepository;

	public User registerUser(User user) {
		return userRepository.save(user);
	}

	public User getUserbyName(String username) {
		Optional<User> userOpt = userRepository.findByUsername(username);
		return userOpt.orElse(new User());
	}
}
