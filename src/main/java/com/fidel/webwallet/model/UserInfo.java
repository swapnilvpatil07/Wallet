
package com.fidel.webwallet.model;

import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;

import com.fidel.webwallet.commons.CommonUtils;

/**
 * @author Swapnil
 *
 */
@Entity
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer custId;
	private String fName;
	private String lName;
	private String address;
	private String contactNo;
	private String cntVerified;
	private String emailId;
	private String emailVerified;
	private String dob;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "userInf")
	private Password password;

	/*
	 * @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch =
	 * FetchType.EAGER) private Set<Role> roles;
	 */

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role")
	private Set<Role> roles;

	@OrderBy
	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "cust_order")
	private SortedSet<OrderDetails> orderDetails;

	public UserInfo() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param custId
	 * @param fName
	 * @param lName
	 * @param address
	 * @param contactNo
	 * @param emailId
	 * @param dob
	 * @param password
	 * @param roles
	 */
	public UserInfo(Integer custId, String fName, String lName, String address, String contactNo, String emailId,
			String dob) {
		super();
		this.custId = custId;
		this.fName = fName;
		this.lName = lName;
		this.address = address;
		this.contactNo = contactNo;
		this.emailId = emailId;
		this.dob = dob;
	}

	public UserInfo(UserInfo userInfo) {
		this.fName = userInfo.getfName();
		this.lName = userInfo.getlName();
		this.contactNo = userInfo.getContactNo();
		this.address = userInfo.getAddress();
		this.emailId = userInfo.getEmailId();
		this.dob = userInfo.getDob();
		this.getUserPassword().setPassword(userInfo.getUserPassword().getPassword());
		this.roles = userInfo.getRoles();
	}

	public Integer getCustId() {
		return custId;
	}

	public void setCustId(Integer custId) {
		this.custId = custId;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * @return the password
	 */
	public Password getUserPassword() {
		if (password == null) {
			password = new Password();
		}
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setUserPassword(Password password) {
		this.password = password;
	}

	/**
	 * @return the roles
	 */
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the fName
	 */
	public String getfName() {
		return fName;
	}

	/**
	 * @param fName the fName to set
	 */
	public void setfName(String fName) {
		this.fName = fName;
	}

	/**
	 * @return the lName
	 */
	public String getlName() {
		return lName;
	}

	/**
	 * @param lName the lName to set
	 */
	public void setlName(String lName) {
		this.lName = lName;
	}

	/**
	 * @return the dob
	 */
	public String getDob() {
		return dob;
	}

	/**
	 * @param dob the dob to set
	 */
	public void setDob(String dob) {
		this.dob = dob;
	}

	/**
	 * @return the orderDetails
	 */
	public SortedSet<OrderDetails> getOrderDetails() {
		if (orderDetails == null) {
			orderDetails = new TreeSet<OrderDetails>();
		}
		return orderDetails;
	}

	/**
	 * @param orderDetails the orderDetails to set
	 */
	public void setOrderDetails(SortedSet<OrderDetails> orderDetails) {
		this.orderDetails = orderDetails;
	}

	/**
	 * @return the cntVerified
	 */
	public String getCntVerified() {
		return cntVerified;
	}

	/**
	 * @param cntVerified the cntVerified to set
	 */
	public void setCntVerified(String cntVerified) {
		this.cntVerified = cntVerified;
	}

	/**
	 * @return the emailVerified
	 */
	public String getEmailVerified() {
		return emailVerified;
	}

	/**
	 * @param emailVerified the emailVerified to set
	 */
	public void setEmailVerified(String emailVerified) {
		this.emailVerified = emailVerified;
	}

	public String validate() {
		String emptyFiled = "";

		if (CommonUtils.isEmpty(emailId)) {
			emptyFiled = "email";
		}

		if (CommonUtils.isEmpty(this.contactNo)) {
			emptyFiled = "contact";
		}

		return emptyFiled;
	}

	@Override
	public String toString() {
		if (fName == null || lName == null) {
			return contactNo + ", " + emailId;
		} else if (fName.isEmpty() || lName.isEmpty()) {
			return contactNo + ", " + emailId;
		}
		return fName + " " + lName + ", " + contactNo + ", " + emailId;
	}
}
