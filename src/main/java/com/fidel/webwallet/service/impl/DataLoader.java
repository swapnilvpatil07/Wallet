/**
 * 
 */
package com.fidel.webwallet.service.impl;

import java.util.Arrays;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fidel.webwallet.commons.Constants;
import com.fidel.webwallet.model.Password;
import com.fidel.webwallet.model.Role;
import com.fidel.webwallet.model.UserInfo;
import com.fidel.webwallet.repository.RoleRepositiory;
import com.fidel.webwallet.repository.UserInfoRepository;
import com.github.javafaker.Faker;

/**
 * @author Swapnil
 *
 */
@Service
public class DataLoader {

	@Autowired
	private UserInfoRepository infoRepository;

	@Autowired
	private RoleRepositiory roleRepositiory;

	private Faker faker;

	public DataLoader() {
		super();
	}

	@PostConstruct
	void loadData() {
		init();
		removeCustomerData();

		UserInfo info;
		Password password;
		Role role;

		for (int i = 1; i < 10; i++) {
			info = new UserInfo();
			password = new Password();
			role = new Role();

			info.setAddress(faker.streetAddress(true));
			info.setCntVerified(Constants.NON_VERIFIED);
			info.setDob(faker.bothify("0#/0#/199#"));
			info.setEmailVerified(Constants.NON_VERIFIED);
			if (i == 1) {
				info.setfName("Prachi");
				info.setlName("Kulkarni");
				info.setContactNo("7709966240");
				info.setEmailId(info.getfName() + "." + info.getlName() + "@fideltech.com");
			} else {
				info.setfName(faker.firstName());
				info.setlName(faker.lastName());
				info.setContactNo("869848430" + i);
				info.setEmailId("user" + i + "@fideltech.com");
			}

			password.setPassword("12345");

			password.setUserInfo(info);
			info.setUserPassword(password);

			role.setName("USER");
			info.setRoles(new HashSet<Role>(Arrays.asList(role)));

			infoRepository.save(info);
		}

	}

	private void init() {
		faker = new Faker();
	}

	@PreDestroy
	void removeCustomerData() {
		infoRepository.deleteAll();
		roleRepositiory.deleteAll();
	}

}
