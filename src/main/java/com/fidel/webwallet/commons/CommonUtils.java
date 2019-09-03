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
	 * Generate QR Code & return QR code as byte array
	 * 
	 * @param text   text to be encoded in QR code
	 * @param width  width of the image
	 * @param height height of the image
	 * @return byte array of an QR code
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

	/**
	 * get the current date & format date to 'dd-MM-yyyy HH:mm:ss'
	 * 
	 * @return formated date as formatted string
	 */
	public static String getDateTime() {
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		return now.format(format);
	}

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

}
