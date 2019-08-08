/**
 * 
 */
package com.fidel.webwallet.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fidel.webwallet.commons.CommonUtils;

/**
 * @author Swapnil
 *
 */
@Entity
public class Password {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer passId;
	private String password;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "cust_id", referencedColumnName = "custId")
	private UserInfo userInf;

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the userInfo
	 */
	public UserInfo getUserInfo() {
		return userInf;
	}

	/**
	 * @param userInfo the userInfo to set
	 */
	public void setUserInfo(UserInfo userInfo) {
		this.userInf = userInfo;
	}

	public String validate() {
		String emptyFiled = "";

		if (CommonUtils.isEmpty(this.password)) {
			emptyFiled = "password";
		}

		return emptyFiled;
	}

}
