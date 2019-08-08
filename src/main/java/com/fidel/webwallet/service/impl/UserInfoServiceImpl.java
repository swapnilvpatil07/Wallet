/**
 * 
 */
package com.fidel.webwallet.service.impl;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.fidel.webwallet.model.Password;
import com.fidel.webwallet.model.RegUserInfo;
import com.fidel.webwallet.model.Role;
import com.fidel.webwallet.model.UserInfo;
import com.fidel.webwallet.repository.UserInfoRepository;
import com.fidel.webwallet.service.UserInfoService;

/**
 * @author Swapnil
 *
 */
@Service
public class UserInfoServiceImpl implements UserInfoService {

	@Autowired
	private UserInfoRepository userInfoRepository;

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	private static final Logger logger = LoggerFactory.getLogger(UserInfoServiceImpl.class);

	private PasswordEncoder encoder = new PasswordEncoder() {

		@Override
		public boolean matches(CharSequence rawPassword, String encodedPassword) {
			// TODO Auto-generated method stub
			return true;
		}

		@Override
		public String encode(CharSequence rawPassword) {
			// TODO Auto-generated method stub
			return rawPassword.toString();
		}
	};

	@Override
	public List<UserInfo> getUserByEmail(String email) {
		Optional<UserInfo> userList = userInfoRepository.getUserByEmail(email);
		if (!userList.isPresent()) {
			return Collections.emptyList();
		}
		return Arrays.asList(userList.get());
	}

	@Override
	public List<UserInfo> getUserByMobile(String contactNo) {
		List<UserInfo> userList = userInfoRepository.getUserByMobileNo(contactNo);
		if (userList.isEmpty()) {
			return Collections.emptyList();
		}
		return userList;
	}

	@Override
	public boolean isEmailAlreadyExist(String email) {
		if (!getUserByEmail(email).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean isContactNumberAlreadyExist(String contactNo) {
		if (!getUserByMobile(contactNo).isEmpty()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean registerUser(RegUserInfo info) {
		try {
			UserInfo userInfo = new UserInfo();
			userInfo.setEmailId(info.getEmail());
			userInfo.setContactNo(info.getContact());
			userInfo.setCntVerified("0");
			userInfo.setEmailVerified("0");

			Password password = new Password();
			// TODO change password encode to bcrypt later
			// password.setPassword(bCryptPasswordEncoder.encode(info.getPassword()));
			// password.setPassword(new EncryptionUtil().encrypt(info.getPassword()));
			password.setPassword(encoder.encode(info.getPassword()));

			password.setUserInfo(userInfo);
			userInfo.setUserPassword(password);

			Role role = new Role();
			role.setName("USER");

			userInfo.setRoles(new HashSet<Role>(Arrays.asList(role)));
			// userInfo.setRoles(new TreeSet<Role>(Arrays.asList(role)));
			// role.setUsers(userInfo);

			userInfoRepository.save(userInfo);

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	/**
	 * 
	 */
	@Override
	public boolean validateUser(@Valid RegUserInfo user) {
		UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
				userDetails, user.getPassword(), userDetails.getAuthorities());

		// authenticationManager.authenticate(usernamePasswordAuthenticationToken);

		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
			logger.debug(String.format("Logged in %s successfully!", user.getEmail()));
			return true;
		}

		return false;
	}

	@Override
	public boolean save(UserInfo userInfo) {
		try {
			userInfoRepository.save(userInfo);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	@Override
	public UserInfo findById(String id, boolean skip) {
		try {
			Optional<UserInfo> user = userInfoRepository.findById(Integer.parseInt(id));
			if (user.isPresent()) {
				UserInfo userInfo = user.get();
				if (skip) {
					userInfo.setRoles(null);
					userInfo.setUserPassword(null);
				}
				return userInfo;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return null;
	}

	@Override
	public UserInfo getAuthenticatedUserInfo() {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		List<UserInfo> list = this.getUserByEmail(authentication.getName());
		if (list.size() == 0) {
			return new UserInfo();
		}
		return list.get(0);
	}

	@Transactional
	@Override
	public void updateVarifiedUser(Integer custId) {
		userInfoRepository.updateMobVerified(custId);
	}

}
