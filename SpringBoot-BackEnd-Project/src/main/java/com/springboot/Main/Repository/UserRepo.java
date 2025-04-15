package com.springboot.Main.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.springboot.Main.Entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>
{
	// username - email ...
	Optional<User> findByEmail(String email);
}
 