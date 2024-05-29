package com.example.sbp.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	
	public SiteUser create(String userName, String pw ,String email) {
		SiteUser user = new SiteUser();
		user.setUsername(userName);
		user.setEmail(email);
		user.setPw(passwordEncoder.encode(pw));
		this.userRepository.save(user);
		return user;
	}
}
