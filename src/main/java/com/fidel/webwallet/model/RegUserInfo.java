/**
 * 
 */
package com.fidel.webwallet.model;

import com.fidel.webwallet.commons.CommonUtils;

/**
 * @author Swapnil
 *
 */
public class RegUserInfo {

	private String password;
	private String email;
	private String contact;

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	public String validate() {
		String emptyFiled = "";

		if (CommonUtils.isEmpty(getEmail())) {
			emptyFiled = "email";
		}

		if (CommonUtils.isEmpty(getContact())) {
			emptyFiled = "contact";
		}

		if (CommonUtils.isEmpty(getPassword())) {
			emptyFiled = "password";
		}
		return emptyFiled;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
