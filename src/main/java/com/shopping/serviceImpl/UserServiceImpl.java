package com.shopping.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.shopping.DTO.UserDTO;
import com.shopping.model.User;
import com.shopping.repository.UserRepository;
import com.shopping.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	

	@Override
	public User findByUserName(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	 public User saveUser(User user) {
		 
		 if (userRepository.findByUsername(user.getUsername()) != null) {
		        throw new RuntimeException("Tài khoản đã tồn tại");
		    }
	      
	        user.setRole("USER");

	        user.setEnabled(true);
	        // Mã hóa mật khẩu trước khi lưu vào cơ sở dữ liệu
	        String encodePassword = passwordEncoder.encode(user.getPassword());
			user.setPassword(encodePassword);
	        User saveUser = userRepository.save(user);
	      

	        return userRepository.save(user);
	    }

	@Override
	 public List<User> findByRole(String role) {
        return userRepository.findByRole(role);
    }

	@Override
	public List<User> getAllRole() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User saveInfor(User user) {
		// TODO Auto-generated method stub
		User user2 = userRepository.findByUsername(user.getUsername());
		user2.setUsername(user.getUsername());
		user2.setAddress(user.getAddress());
		user2.setCity(user.getCity());
		user2.setEmail(user.getEmail());
		user2.setTelephone(user.getTelephone());
		user2.setFullName(user.getFullName());
		return userRepository.save(user2);
	}

	
	
	





}
