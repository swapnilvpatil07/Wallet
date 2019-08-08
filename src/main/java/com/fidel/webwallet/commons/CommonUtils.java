/**
 * 
 */
package com.fidel.webwallet.commons;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

/**
 * @author Swapnil
 *
 */
@Component
public class CommonUtils {

	/**
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isNull(String element) {
		if (element == null) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param element
	 * @return
	 */
	public static boolean isEmpty(String element) {
		if (element == null) {
			return true;
		}
		if (element.isEmpty()) {
			return true;
		}
		return false;
	}

	/**
	 * 
	 * @param string
	 * @return
	 */
	public static String encodeString(String string) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		return passwordEncoder.encode(string);
	}

	/**
	 * 
	 * @param text
	 * @param width
	 * @param height
	 * @return
	 */
	public static byte[] getQRCodeImage(String text, int width, int height) {
		try {
			QRCodeWriter qrCodeWriter = new QRCodeWriter();
			BitMatrix bitMatrix = qrCodeWriter.encode(text, BarcodeFormat.QR_CODE, width, height);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			MatrixToImageWriter.writeToStream(bitMatrix, "png", byteArrayOutputStream);
			return byteArrayOutputStream.toByteArray();
		} catch (Exception e) {
			return null;
		}
	}

	public static String getDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return now.format(format);
	}
}
