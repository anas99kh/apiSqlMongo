package com.genesis.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genesis.api.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
	boolean existsByEmail(String email);
}
