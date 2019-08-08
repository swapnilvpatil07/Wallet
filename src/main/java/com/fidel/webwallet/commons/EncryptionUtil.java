/**
 * 
 */
package com.fidel.webwallet.commons;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author Swapnil
 *
 */
public class EncryptionUtil {
	private final static String KEY = "Swapnil@98484304";
	private Cipher ecipher;
	private Cipher dcipher;

	/**
	 * 
	 * @param key
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 * @throws InvalidKeyException
	 */
	public EncryptionUtil(SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException {
		ecipher = Cipher.getInstance("AES");
		dcipher = Cipher.getInstance("AES");
		ecipher.init(Cipher.ENCRYPT_MODE, key);
		dcipher.init(Cipher.DECRYPT_MODE, key);
	}

	/**
	 * 
	 * @throws InvalidKeyException
	 * @throws NoSuchAlgorithmException
	 * @throws NoSuchPaddingException
	 */
	public EncryptionUtil() throws InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException {
		this(new SecretKeySpec(KEY.getBytes(), "AES"));
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String encrypt(String str) throws Exception {

		byte[] byteArray = str.getBytes("UTF8");
		byte[] enc = ecipher.doFinal(byteArray);
		return Base64.getEncoder().encodeToString(enc);
	}

	/**
	 * 
	 * @param str
	 * @return
	 * @throws Exception
	 */
	public String decrypt(String str) throws Exception {
		byte[] dec = Base64.getDecoder().decode(str);
		byte[] byteArray = dcipher.doFinal(dec);
		return new String(byteArray, "UTF8");
	}
}
