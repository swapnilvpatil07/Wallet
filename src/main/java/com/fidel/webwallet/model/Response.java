/**
 * 
 */
package com.fidel.webwallet.model;

import org.springframework.stereotype.Component;

import com.fidel.webwallet.commons.Constants;

/**
 * @author Swapnil
 *
 */
@Component
public class Response {
	private String status;
	private Integer errorCode;
	private String errorMsg;
	private UserInfo data;

	public Response() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param status
	 * @param errorCode
	 * @param erroeMsg
	 */
	public Response(String status, Integer errorCode, String erroeMsg) {
		super();
		this.status = status;
		this.errorCode = errorCode;
		this.setErrorMsg(erroeMsg);
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMsg
	 */
	public String getErrorMsg() {
		return errorMsg;
	}

	/**
	 * @param errorMsg the errorMsg to set
	 */
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}

	/**
	 * @return the data
	 */
	public UserInfo getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(UserInfo data) {
		this.data = data;
	}

	public Response clear() {
		this.setErrorMsg(Constants.EMPTYSTR);
		this.errorCode = -1;
		this.status = Constants.EMPTYSTR;
		return this;
	}

}
