package com.fidel.webwallet.service.impl;

import java.util.Random;

import org.springframework.stereotype.Service;

import com.fidel.webwallet.service.OneTimePassword;
import com.twilio.Twilio;
import com.twilio.base.ResourceSet;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

@Service
public class OneTimePasswordImpl implements OneTimePassword {

	private static final String AUTH_TOKEN = "f259a0f4fe30e82935e62839f38ecdf4";
	private static final String ACCOUNT_SID = "ACb46c18fbb4534db509b1595a960abb84";
	private static final String TWILIO_NUMBER = "+12055764162";
	private Integer otp;

	public OneTimePasswordImpl() {
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
	}

	@Override
	public boolean send(String number) {
		// TODO: Mobile number validation
		Message msg = Message
				.creator(new PhoneNumber("+91" + number), new PhoneNumber(TWILIO_NUMBER), "OTP: " + getOtp(0)).create();

		ResourceSet<Message> messages = Message.reader().read();
		Message message = msg;
		for (Message message1 : messages) {
			message = message1;
			System.out.println(message1.getSid() + " : " + message1.getStatus());
		}

		/*
		 * messages.iterator() .forEachRemaining(message2 ->
		 * System.out.println(message2.getSid() + " : " + message2.getStatus()));
		 */
		return message.getStatus().equals(Message.Status.DELIVERED) ? true : false;
	}

	/**
	 * 
	 * @param i
	 * @return
	 */
	private String getOtp(int otpTmp) {
		Random random = new Random();
		otp = otpTmp;

		int length = String.valueOf(otp).length();
		if (length == 4) {
			return otp.toString();
		} else {
			if (length < 4) {
				getOtp(random.nextInt(9999));
			}
		}
		return otp.toString();
	}

	@Override
	public boolean verify(String otp) {
		if (this.otp == Integer.parseInt(otp)) {
			return true;
		}
		return false;
	}

}
