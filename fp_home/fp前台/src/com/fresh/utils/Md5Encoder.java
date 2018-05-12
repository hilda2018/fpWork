/**
 * 
 */
package com.fresh.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import org.apache.commons.codec.binary.Hex;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author lnlh
 * 
 */
public class Md5Encoder {

	private final static Log logger = LogFactory
			.getLog(Md5Encoder.class);

	//
	private final static String ALGORITHM = "MD5";

	/**
	 * 
	 */
	private Md5Encoder() {

	}

	/**
	 * md5 encode.
	 * @param rawPass
	 * @return
	 */
	public static String encodePassword(String rawPass) {
		MessageDigest messageDigest = null;

		try {
			messageDigest = MessageDigest.getInstance(ALGORITHM);
		} catch (NoSuchAlgorithmException e) {
			logger.error(e.getMessage(), e);
		}

		byte[] digest = messageDigest.digest(rawPass.getBytes());

		if (logger.isDebugEnabled()) {
			logger.debug("encode password is:"
					+ new String(Hex.encodeHex(digest)));
		}

		return new String(Hex.encodeHex(digest));
	}
}
