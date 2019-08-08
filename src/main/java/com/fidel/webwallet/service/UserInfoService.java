/**
 * 
 */
package com.fidel.webwallet.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Service;

import com.fidel.webwallet.model.RegUserInfo;
import com.fidel.webwallet.model.UserInfo;

/**
 * @author Swapnil
 *
 */
@Service
public interface UserInfoService {

	List<UserInfo> getUserByEmail(String email);

	List<UserInfo> getUserByMobile(String contactNo);

	boolean isEmailAlreadyExist(String email);

	boolean isContactNumberAlreadyExist(String contactNo);

	boolean registerUser(RegUserInfo info);

	boolean validateUser(@Valid RegUserInfo user);

	boolean save(UserInfo userInfo);

	UserInfo findById(String id, boolean skip);

	UserInfo getAuthenticatedUserInfo();

	void updateVarifiedUser(Integer custId);
}
