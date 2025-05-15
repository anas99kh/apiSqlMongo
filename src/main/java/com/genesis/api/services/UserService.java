package com.genesis.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import com.genesis.api.models.User;
import com.genesis.api.repositories.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	public Optional<User> getUserById(Long id){
		return userRepository.findById(id);
	}
	
	public User createUser(User user) {
		if(userRepository.existsByEmail(user.getEmail())) {
			throw new DuplicateKeyException("X Cette user exite déjà");
		}
		return userRepository.save(user);
	}
	
	
	public void deleteUser(Long id) {
		userRepository.deleteById(id);
	}
	
	
}
