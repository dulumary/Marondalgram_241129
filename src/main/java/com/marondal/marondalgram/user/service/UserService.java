package com.marondal.marondalgram.user.service;

import org.springframework.stereotype.Service;

import com.marondal.marondalgram.common.SHA256HashingEncoder;
import com.marondal.marondalgram.user.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public boolean addUser(
			String loginId
			, String password
			, String name
			, String email) {
		
		String encodingPassword = SHA256HashingEncoder.encode(password);
		
		int count = userRepository.insertUser(loginId, encodingPassword, name, email);
		
		if(count == 1) {
			return true;
		} else {
			return false;
		}
	}
	
	public boolean isDuplicateId(String loginId) {
		
		int count = userRepository.selectCountLoginId(loginId);
		
		if(count > 0) {
			return true;
		} else {
			return false;
		}
	}
	
	
	
	

}