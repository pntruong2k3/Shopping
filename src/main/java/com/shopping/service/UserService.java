package com.shopping.service;


import java.util.List;

import com.shopping.DTO.UserDTO;
import com.shopping.model.User;

public interface UserService {
	
	User findByUserName(String userName);

	User saveUser(User user);
	
	List<User> findByRole(String role);

	List<User> getAllRole();
	
	User saveInfor(User user);

	
}
