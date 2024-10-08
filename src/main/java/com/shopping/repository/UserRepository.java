package com.shopping.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.shopping.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long > {
	User findByUsername(String userName);
	User findByEmail (String email);
    List<User> findByRole(String role);

}
