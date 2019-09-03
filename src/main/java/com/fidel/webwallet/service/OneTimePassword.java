/**
 * 
 */
package com.fidel.webwallet.service;

/**
 * @author Swapnil
 *
 */
public interface OneTimePassword {

	boolean send(String number);

	boolean verify(String otp);

	String getOtp(int otpTmp);

}
