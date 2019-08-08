package com.fidel.webwallet.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fidel.webwallet.model.UserInfo;
import com.fidel.webwallet.repository.UserInfoRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private UserInfoRepository userInfoRepository;

	@Override
	public UserDetails loadUserByUsername(String email) {
		Optional<UserInfo> users = userInfoRepository.getUserByEmail(email);
		users.orElseThrow(() -> new UsernameNotFoundException("User not found."));

		CustomUserDetails custDetails = users.map(user -> new CustomUserDetails(user)).get();
		return custDetails;
	}
}
