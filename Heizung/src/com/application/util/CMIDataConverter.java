package com.application.util;

import org.apache.log4j.Logger;

public class CMIDataConverter {

	private static CMIDataConverter instance;
	private static final Logger logger = Logger.getLogger(CMIDataConverter.class);

	public static void main(String[] args) {
		CMIDataConverter converter = new CMIDataConverter();
		byte lowByte = (byte) 238;
		byte highByte = (byte) 255;
		System.out.println("low: " + lowByte);
		System.out.println("high: " + highByte);
		converter.convert(lowByte, highByte);

	}

	public static CMIDataConverter getInstance() {

		if (instance == null) {
			instance = new CMIDataConverter();
		}

		return instance;

	}

	private CMIDataConverter() {

	}

	public float convert(byte lowByte, byte highByte) {

		if (highByte == -1) {
			return convertNegative(lowByte, highByte);
		} else {
			return convertPositive(lowByte, highByte);
		}
	}

	private float convertPositive(byte lowByte, byte highByte) {

		String temp1 = Integer.toHexString(lowByte >> 4 & 0x0F);
		int v1 = Integer.valueOf(temp1, 16) << 4;
		if (logger.isInfoEnabled())
			logger.info("LowByte Nibble 1: " + temp1);

		String temp2 = Integer.toHexString(lowByte & 0x0F);
		int v2 = Integer.valueOf(temp2, 16);
		if (logger.isInfoEnabled())
			logger.info("LowByte Nibble 2: " + temp2);

		String temp3 = Integer.toHexString(highByte);
		int v3 = Integer.valueOf(temp3, 16) << 8;
		if (logger.isInfoEnabled())
			logger.info("HighByte Nibble 1: " + temp3);

		// -------------------------------------------------------

		String erg = String.valueOf(v1 + v2 + v3);
		Float result = Float.valueOf(erg) / 10;

		if (logger.isInfoEnabled())
			logger.info("Result: " + result);

		return result;

	}

	private float convertNegative(byte lowByte, byte highByte) {

		String erg = String.valueOf(lowByte);

		float result = Float.valueOf(erg) / 10;

		if (logger.isInfoEnabled())
			logger.info("Result: " + result);

		return result;

	}

}
