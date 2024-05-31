package com.example.sbp.user;

import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.sbp.DataNotFoundException;

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
	
	public SiteUser getUser(String username) {
		Optional<SiteUser> siteUser = this.userRepository.findByUsername(username);
		if(siteUser.isPresent()) {
			return siteUser.get();
		}else {
			throw new DataNotFoundException("siteuser not found");
		}
	}
}
